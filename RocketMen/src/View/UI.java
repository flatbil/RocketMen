package View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.DatabaseMetaData;

import java.sql.Connection;
import java.sql.ResultSet;

import Model.Import;
import Model.Table;

public class UI {
	private Import myImport;
	private Connection myConnection;
    public UI(Connection connection) throws SQLException {
        myConnection = connection;
    	mainMenu();
    }
    
    public void mainMenu() throws SQLException {
        int selection = 0;
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to RocketMaster 3000. \nWhat would you like to do?");
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
                mainMenu();
                break;    
            case 2:
                //Display d = new Display();
            	displayMenu();
                mainMenu();
                break;
            case 3:
            	displayMenuGraphically();
            	mainMenu();
            case 4:
            	deleteMenu();
            	mainMenu();
                break;
            case 5:
                System.out.println("Thanks for using RocketMaster 3000");
                break;
        }
        
    }
    /*
     * @author William Almond
     * 
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
    			myImport = new Import(selection);
    		}
//    	}
    }
    /*
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
    		  Display theDisplay = new Display(selectOption(selection));
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
    
    /*
     *@author William Almond 
     */
    private void displayMenuGraphically() throws SQLException{
    	Scanner console3 = new Scanner(System.in);
    	System.out.println("Enter the number of the launch for graphical display:");
    	int i = printOptions();
    	int selection = 0;
    	if(console3.hasNextInt()){
    		selection = console3.nextInt();
    		if(selection <= i && selection >= 1){
    		DisplayGraphically theDisplay = new DisplayGraphically(selectOption(selection));
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
    /*
     * @author William Almond
     */
    private void deleteMenu() throws SQLException{
    	Scanner console4 = new Scanner(System.in);
    	System.out.println("Enter the number of the Table to be deleted:");
    	int i = printOptions();
    	if(console4.hasNextInt()){
    		int selection = console4.nextInt();
    		if(selection <= i && selection >= 1){
    		DBtableDelete del = new DBtableDelete(selectOption(selection));
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
    /*
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
