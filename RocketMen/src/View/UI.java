package View;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Import;
import Model.Table;

public class UI {
	private Import myImport;
    public UI() throws SQLException {
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
    private void importMenu(){
    	Scanner console2 = new Scanner(System.in);
    	System.out.println("Enter the name of the table:");
    	//String selection = console2.next();
//    	if(console2.next() == "0"){
//    		mainMenu();
//    	}
//    	else {
    		String selection = console2.next();
    		myImport = new Import(selection);
//    	}
    }
    /*
     *@author William Almond 
     */
    private void displayMenu(){
    	Scanner console3 = new Scanner(System.in);
    	System.out.println("Enter the name of the launch:");
    	String selection = console3.next();
    	Display theDisplay = new Display(selection);
    }
    
    /*
     *@author William Almond 
     */
    private void displayMenuGraphically(){
    	Scanner console3 = new Scanner(System.in);
    	System.out.println("Enter the name of the launch:");
    	String selection = console3.next();
    	DisplayGraphically gd = new DisplayGraphically(selection);
    }
    /*
     * @author William Almond
     */
    private void deleteMenu() throws SQLException{
    	Scanner console4 = new Scanner(System.in);
    	System.out.println("Enter the name of the launch to Delete:");
    	String selection = console4.next();
    	DBtableDelete del = new DBtableDelete(selection);
    	System.out.println("Deletion Complete!");
    }
    
}
