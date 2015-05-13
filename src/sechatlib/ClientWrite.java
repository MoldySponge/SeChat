package sechatlib;

import java.io.*;
import java.util.*;
import javax.net.ssl.*;

public class ClientWrite implements Runnable{
   private SSLSocket client;
   private String message;
   
   public ClientWrite(SSLSocket newClient, String newMessage){
	   client = newClient; 
	   message = newMessage;
	   new Thread(this).start();
   }
   
   public void run(){						
			try{
				OutputStream outToServer = client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);				
				out.writeUTF(message);				
			}catch(IOException ex){
				System.out.println("IOException in writeToServer: " + ex.getMessage());
			}		
   }   
 }

