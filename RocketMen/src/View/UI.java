package View;

import java.util.Scanner;

import Model.Import;

public class UI {

    public UI() {
        mainMenu();
    }
    
    public void mainMenu() {
        int selection = 0;
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to RocketMaster 3000. \nWhat would you like to do?");
        System.out.println("1. Import Data");
        System.out.println("2. Show Rocket launch Data");
        System.out.println("3. Delete Rocket launch Data");
        System.out.println("4. Exit");
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
                
            	deleteMenu();
            	mainMenu();
                break;
            case 4:
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
    	String selection = console2.next();
    	Import theImport = new Import(selection);
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
     * @author William Almond
     */
    private void deleteMenu(){
    	Scanner console4 = new Scanner(System.in);
    	System.out.println("Enter the name of the launch to Delete:");
    	String selection = console4.next();
    	System.out.println("Sorry, Can't Delete your table "+ selection +",\n"
    			+ " Still working on this :-(");
    }
    
}
