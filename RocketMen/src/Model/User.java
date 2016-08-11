package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to log in new an existing users.
 * @author Will Almond
 *
 */
public class User {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Winston1";
    private static final String USER_DB = "@'localhost'";
    private String myUsername;
    private String myPassword;
    private Connection myConnection;
    
    public User(String username, String password,Connection connection) throws SQLException{
    	myUsername = username;
    	myPassword = password;
    	//temp connection for testing.
    	myConnection = testConnection();
    	createUser(myUsername, myPassword, myConnection);
    	
    }
    //temp method for testing.
    private Connection testConnection() throws SQLException{
		Connection myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    	
    	return myConnection;
    }
    /**
     * Method for creating user as required.
     * @throws SQLException 
     */
    public void createUser(String username, String password, Connection connection) throws SQLException{
    	Statement statement = connection.createStatement();
		String SQL_User_Text = "CREATE USER IF NOT EXISTS " 
    	+ "'"+username+"'" + USER_DB +";";
		statement.executeUpdate(SQL_User_Text);
		System.out.println("Welcome " + username+"!");
		//rs.close();
		SQL_User_Text = "SET PASSWORD FOR "+ "'"+username+"'" + USER_DB + " = " + "'"+password+"'"+";" ;
		statement.executeUpdate(SQL_User_Text);
		statement.close();
    }
}
