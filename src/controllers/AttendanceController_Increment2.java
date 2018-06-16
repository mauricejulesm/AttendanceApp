package controllers;

import helpers.InputHelper;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author Jules Maurice Mulisa
 */
public class AttendanceController_Increment2{
    private final Repository repositoryObj;
    
    /**
     * creating a new Repository from swipes file if specified by the user
     */
        
    public AttendanceController_Increment2() {
        
        InputHelper inputHelper = new InputHelper();
        char choice = inputHelper.readCharacter("Load an already existing Swipes File (Y/N)?");
        if (choice == 'Y' || choice     == 'y') {
            String fileName = inputHelper.readString("Enter filename, Make sure to add File Extension");
            this.repositoryObj = new Repository(fileName);
        }
        else {
            this.repositoryObj = new Repository();
        }
    }
   
    /**
     *
     */
    public void run() {
        boolean finished = false;
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    listSwipes();
                    break;
                case 'C': 
                    listSwipesByCardIdOrderedByDateTime(); // 
                    break;                    
                case 'D': 
                    listSwipeStatistics(); //
                    break;
                case 'Q': 
                    finished = true;
            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");       
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }    
    
    /**
     * adding new swipes to the repositoryObj using the addSwipe() method.
     */
    private void addSwipe() {
        InputHelper inputHelper = new InputHelper();
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "=========");
        String room = "General";
        char c = inputHelper.readCharacter("Are you a Visitor? (Y/N)");
        if (c == 'Y' || c == 'y') {
            String cardId = inputHelper.readString("Enter Your Card Id"); 
            String visitorName = inputHelper.readString("Enter Your Visitor Name"); 
            String visitorCompany = inputHelper.readString("Enter Your Visitor Company");
            
            VisitorSwipe vSwipe = new VisitorSwipe(cardId, room, visitorName, visitorCompany);
            repositoryObj.add(vSwipe);
        }
        else {
            String cardId = inputHelper.readString("Enter Your Card Id"); 
            Swipe swipe = new Swipe(cardId,room);
            repositoryObj.add(swipe);
            
        }      
    }
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");  

        
    }      
      

    private void listSwipesByCardIdOrderedByDateTime() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
    }    
    
    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
    }
}
