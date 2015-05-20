package sechatlib;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class ClientRead extends Thread {
	
	private SSLSocket client = null;
	private User user;
	
	private InputStream inFromServer;
	private DataInputStream in;
	
	private ClientWrite writer;
	private String message;
	private String []friends;
	private int serverReady = 0;
	
	public ClientRead(SSLSocket newClient, User newUser){
		client = newClient;
		user = newUser;		
		this.start();
	}
	
	public void run(){
		while(true){
			try{				
				inFromServer = client.getInputStream();
				in = new DataInputStream(inFromServer);
				message = in.readUTF();
				System.out.println("Ready message: " + message);
				if(message.charAt(0) == 'r'){
					writer = new ClientWrite(client, "u");
					serverReady = 1;
				}
				if(serverReady != 0){
					if(ProtocolDirector.returnedUserName(message)){
						user.setUserName(ProtocolDirector.returnProtocolContents(message));
						}
					else if(ProtocolDirector.returnedFriendList(message)){
						friends = ProtocolDirector.returnParsedFriendList(message);
						user.setFriends(friends);
					}
				}				
		}catch(IOException ex){
			System.out.print("IOException in readFromServer: " + ex.getMessage());			
		}
	}/*catch(InterruptedException e){
			//System.out.println("Timer exception");
			//break;
		}*/
	}

}
