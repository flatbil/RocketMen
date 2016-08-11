package View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.DatabaseMetaData;

import java.sql.Connection;
import java.sql.ResultSet;

import Model.Import;
import Model.Table;
import Model.User;
/**
 * UI Class that contains the menus for the user to interact.
 */
public class UI {
	private Connection myConnection;
    /**
     * Constructor for the UI class.
     */
	public UI(Connection connection) throws SQLException {
        myConnection = connection;
    	splashScreen();
    }
    /**
     * @author Carl Huntington
     * The menu that is accessible only by the administrator with full permissions.
     * @throws SQLException
     */
    public void adminMenu() throws SQLException {
        int selection = 0;
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to RocketMaster 3000. \nWhat would you like to do?");
        System.out.println("Signed in as: Guest");
        System.out.println("1. Import Data");
        System.out.println("2. Show Rocket launch Data");
        System.out.println("3. Show Rocket launch Data Graphically!");
        System.out.println("4. Delete Rocket launch Data");
        System.out.println("5. Exit");
        System.out.print("> ");
        selection = console.nextInt();
        switch (selection) {
            case 1:
                importMenu();
                adminMenu();
                break;    
            case 2:
                //Display d = new Display();
            	displayMenu();
                adminMenu();
                break;
            case 3:
            	displayMenuGraphically();
            	adminMenu();
            case 4:
            	deleteMenu();
            	adminMenu();
                break;
            case 5:
                System.out.println("Thanks for using RocketMaster 3000");
                break;
        }
        
    }
    /**
     * @author Carl Huntington
     * Splash screen that is accessible to all users.
     * @throws SQLException
     */
    public void splashScreen() throws SQLException {
        int selection = 0;
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to RocketMaster 3000. \nWhat would you like to do?");
        System.out.println("1. Sign in.");
        System.out.println("2. Sign up for a user account.");
        System.out.println("3. Sign in as a guest");
        System.out.println("4. Exit");
        System.out.print("> ");
        selection = console.nextInt();
        switch (selection) {
            case 1:
                logIn();
                break;    
            case 2:
            	logIn();
                break;
            case 3:
                guestMenu();
                break;
            case 4:
                break;
        }
    }
    /**
     * @author Carl Huntington
     * LogIn menu that is displayed when you log in.
     * @throws SQLException
     */
    public void logIn() throws SQLException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to RocketMaster 3000. \nWhat would you like to do?");
        System.out.print("Enter your username: ");
        String username = console.next();
        System.out.print("Enter your password: ");
        String password = console.next();
        if(username.equals("admin") && password.equals("admin")) {
            adminMenu();
        } else {
        	new User(username, password, myConnection);
        	adminMenu();
        }        
    }

    /**
     * @author Carl Huntington
     * @throws SQLException
     */
    public void guestMenu() throws SQLException {
        int selection = 0;
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to RocketMaster 3000. \nWhat would you like to do?");
        System.out.println("Signed in as: Guest");
        System.out.println("1. Show Rocket launch Data");
        System.out.println("2. Show Rocket launch Data Graphically!");
        System.out.println("3. Exit");
        System.out.print("> ");
        selection = console.nextInt();
        switch (selection) {
            case 1:
                //Display d = new Display();
                displayMenu();
                guestMenu();
                break;
            case 2:
                displayMenuGraphically();
                guestMenu();
            case 3:
                System.out.println("Thanks for using RocketMaster 3000");
                break;
        }
        
    }
    
    
    /**
     * @author William Almond
     * This menu asks the user to enter the name of the table to import.
     * As long as the name isn't '0' then it will send the name and connection
     * to the Import class.
     */
    private void importMenu() throws SQLException{
    	Scanner console2 = new Scanner(System.in);
    	System.out.println("Enter the name of the table for import:");
    	//String selection = console2.next();
//    	if(console2.next() == "0"){
//    		mainMenu();
//    	}
//    	else {
    		String selection = console2.next();
    		if(selection.equals("0")){
    			return;
    		} else {
    			new Import(selection, myConnection);
    		}
//    	}
    }
    /**
     *@author William Almond 
     */
    private void displayMenu() throws SQLException{
    	Scanner console3 = new Scanner(System.in);
    	System.out.println("Enter the number of the launch for display:");
    	int i = printOptions();
    	int selection = 0;
    	if(console3.hasNextInt()){
    		selection = console3.nextInt();
    		if(selection <= i && selection >= 1){
    		  new Display(selectOption(selection), myConnection);
    		} else if(selection == 0){ 
        		return;
        		}else { 
    			System.out.println("That wasn't one of the numbers I gave you...\n");
    			displayMenu();
    		}
    	} else {
    		System.out.println("You have to type a number:");
    		displayMenu();
    	}
    	
    }
    
    /**
     *@author Carl Huntington
     */
    private void displayMenuGraphically() throws SQLException{
    	Scanner console3 = new Scanner(System.in);
    	System.out.println("Enter the number of the launch for graphical display:");
    	int i = printOptions();
    	int selection = 0;
    	if(console3.hasNextInt()){
    		selection = console3.nextInt();
    		if(selection <= i && selection >= 1){
    		new DisplayGraphically(selectOption(selection), myConnection);
    		} else if(selection == 0){ 
        		return;
        		}else {
    			System.out.println("That wasn't one of the numbers I gave you...\n");
    			displayMenuGraphically();
    		}
    	} else {
    		System.out.println("You have to type a number in the range shown:\n");
    		displayMenuGraphically();
    	}
    }
    /**
     * @author William Almond
     * This menu gives SQL command to delete a table from the Database.
     */
    private void deleteMenu() throws SQLException{
    	Scanner console4 = new Scanner(System.in);
    	System.out.println("Enter the number of the Table to be deleted:");
    	int i = printOptions();
    	if(console4.hasNextInt()){
    		int selection = console4.nextInt();
    		if(selection <= i && selection >= 1){
    		new DBtableDelete(selectOption(selection), myConnection);
    		} else if(selection == 0){ 
    			return;
    		}else{
    			System.out.println("That wasn't one of the numbers I gave you...\n");
    			deleteMenu();
    		}
    		System.out.println("Deletion Complete!");
    	} else {
    		System.out.println("You must type a number in the range shown:");
    		deleteMenu();
    	}
    	
    }
    /**
     * @author William Almond
     * Method prints all the options to the menu screen for the user to select.
     * returns an integer that is the number of tables.
     */
    private int printOptions() throws SQLException{
    	DatabaseMetaData meta = (DatabaseMetaData) myConnection.getMetaData();
    	ResultSet res = meta.getTables(null, null, null, 
       	     new String[] {"TABLE"});
    	int i = 0;
    	
    	while(res.next()){
    		System.out.println(i+1 + ". \t" + res.getString(3));
    		i++;
    	}
    	res.close();
    	return i;
    }
    private String selectOption(int i) throws SQLException{
    	DatabaseMetaData meta = (DatabaseMetaData) myConnection.getMetaData();
    	ResultSet res = meta.getTables(null, null, null, 
       	     new String[] {"TABLE"});
    	ArrayList<String> theList = new ArrayList<String>();
    	String theIndex;
    	while(res.next()){
    		theList.add(res.getString(3));
    	}
    	theIndex = theList.get(i-1);
    	res.close();
    	return theIndex;
    }
    
}
