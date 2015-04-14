package sechatlib;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLDatabaseController {
    Connection conn = null;
    
    public void connectToDatabase(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	}catch(Exception ex){
    		System.out.println("At Driver:" + ex);
    	}
    	try{
    		conn = DriverManager.getConnection("jdbc:mysql://107.170.220.121:3306/sechat","", "");    										
    	}catch(SQLException ex){
    		System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
    }
    public boolean checkForUsernameConflict(String username){
    	try{
    	Statement stmnt = conn.createStatement();
    	String usernameQuery = "SELECT * FROM Users WHERE UserID ='" +username + "';";
    	ResultSet results = stmnt.executeQuery(usernameQuery);
    	int numberofcols = results.getMetaData().getColumnCount();    	
    	while(results.next()){
    		if(results.getString("UserID").compareToIgnoreCase(username) == 0){
    			return false;
    		}
    	}
    	results.close();
    	stmnt.close();
    	conn.close();
    	}catch(SQLException ex){
    		return false;
    	}
    	return true;
    }
}
