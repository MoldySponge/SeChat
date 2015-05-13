package sechatlib;

public class ProtocolDirector {
	public static String interpretMessage(String message){
		if(returnedUserName(message)){
			return returnProtocolContents(message);
		}
		return "blah";
	}
	
	public static boolean serverReady(String message){
		if(message.charAt(0) == 'r'){
			return true;
		}
		return false;
	}
	
	public static boolean returnedUserName(String message){
		if(message.charAt(0) == 'u'){
			return true;
		}
		else
			return false;
	}
	
	public static String returnProtocolContents(String message){
		return message.substring(1);
	}
}
