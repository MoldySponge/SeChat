package sechatlib;

import java.io.*;
import java.util.Arrays;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class CredentialChecker {

	private SSLSocketFactory sslSocketFactory;
	private SSLSocket sslSocketClient;
	
	private boolean connectToServer(){
		try{
			System.setProperty("javax.net.ssl.trustStore", "keystore.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "294337");
			//System.setProperty("javax.net.debug", "all");
			
			sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			sslSocketClient = (SSLSocket)sslSocketFactory.createSocket("107.170.220.121",3456);
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
			OutputStream outToServer = sslSocketClient.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(fullCheckString);
			//waits for response
			InputStream inFromServer = sslSocketClient.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			String result = in.readUTF();
			
			if(result.compareTo("true") == 0){
				return true;				
			}
			else{
				sslSocketClient.close();
				return false;
			}			
		}catch(IOException ex){
			System.out.println("Error Communicating with server: " + ex.getMessage());
			try{sslSocketClient.close();}catch(IOException x){System.out.println("Problem closing connection: " + x.getMessage());};
			return false;
		}
	}
	public boolean checkLogIn(String userName, byte []password){
		connectToServer();
		String fullLogInString = "l"+userName;
		String result = "false";
		System.out.println("here");
		try{
			OutputStream outToServer = sslSocketClient.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(fullLogInString);
			for(int i = 0;i<password.length;i++){
				out.writeByte(password[i]);
			}
			System.out.println(Arrays.toString(password));
			
			InputStream inFromServer = sslSocketClient.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			result = in.readUTF();

			if(result.compareTo("true") == 0){
				System.out.println(result);
				return true;
			}
			sslSocketClient.close();
			return false;
		}catch(IOException ex){
			System.out.println("Error communicating with server during LogIn process: " + ex.getMessage());
			try{sslSocketClient.close();}catch(IOException x){System.out.println("Problem closing connection: " + x.getMessage());};
			return false;
		}
	}
	public boolean sendPasswordHash(byte []password){
		try{
			OutputStream outToServer = sslSocketClient.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			for(int i = 0;i<password.length;i++){
				out.writeByte(password[i]);
			}
		    sslSocketClient.close();
			return true;
		}catch(IOException ex){
			System.out.println("Error in password sending: " + ex.getMessage());
			try{sslSocketClient.close();}catch(IOException x){System.out.println("Problem closing connection: " + x.getMessage());};
			return false;
		}
	}
	
	public SSLSocket getSSLConnection(){
		return sslSocketClient;
	}
}
