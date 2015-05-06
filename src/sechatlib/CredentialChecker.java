package sechatlib;

import java.io.*;
import java.net.*;

public class CredentialChecker {

	private Socket client;
	
	private boolean connectToServer(){
		try{
			client = new Socket("107.170.220.121", 3456);
			return true;
		}catch(IOException ex){
			System.out.println("Error connecting to server: " + ex.getMessage());
		}
		return false;
	}
	public boolean checkForUserNameConflict(String userName){
		connectToServer();
		String fullCheckString = "c"+userName;
		try{
			//sends username to be checked out
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(fullCheckString);
			//waits for response
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			String result = in.readUTF();
			
			if(result.compareTo("true") == 0){
				return true;				
			}
			else{
				client.close();
				return false;
			}			
		}catch(IOException ex){
			System.out.println("Error Communicating with server: " + ex.getMessage());
			try{client.close();}catch(IOException x){System.out.println("Problem closing connection: " + x.getMessage());};
			return false;
		}
	}
	public boolean checkLogIn(String userName, byte []password){
		connectToServer();
		String fullLogInString = "l"+userName;
		String result = "false";
		System.out.println("here");
		try{
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(fullLogInString);
			out.write(password);
			
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			result = in.readUTF();
			if(result.compareTo("true") == 0){
				client.close();
				return true;
			}
			client.close();
			return false;
		}catch(IOException ex){
			System.out.println("Error communicating with server during LogIn process: " + ex.getMessage());
			try{client.close();}catch(IOException x){System.out.println("Problem closing connection: " + x.getMessage());};
			return false;
		}
	}
	public boolean sendPasswordHash(byte []password){
		try{
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);			
		    out.write(password);
		    client.close();
			return true;
		}catch(IOException ex){
			System.out.println("Error in password sending: " + ex.getMessage());
			try{client.close();}catch(IOException x){System.out.println("Problem closing connection: " + x.getMessage());};
			return false;
		}
	}
}
