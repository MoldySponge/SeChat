package sechatlib;

public class ProtocolDirector {
	
	private static String []friends;
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
		
		return false;
	}
	
	public static boolean returnedFriendList(String message){
		if(message.charAt(0) == 'f'){
			return true;
		}
		return false;
	}
	
	public static String[] returnParsedFriendList(String message){
		int numberOfFriends = countFriends(message);
		friends = new String[numberOfFriends];
		message = message.substring(1);
		for(int i = 0;i<numberOfFriends;i++){
			friends[i] = message.substring(0, commaAt(message));
			message = message.substring(commaAt(message)+1);					
		}		
		return friends;
	}
	
	public static int countFriends(String newParseList){
		int count = 0;
		for(int i = 0; i < newParseList.length();i++){
			if(newParseList.charAt(i) == ','){
				count++;
			}
		}
		return count;
	}
	
	public static int commaAt(String newParseList){
		for(int i = 0; i < newParseList.length();i++){
			if(newParseList.charAt(i) == ','){
				return i;
			}
		}
		return 0;
	}
	
	public static String returnProtocolContents(String message){
		return message.substring(1);
	}
}
