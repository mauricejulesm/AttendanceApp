package controllers;

import helpers.InputHelper;
import java.util.Iterator;
import model.Swipe;
import repositories.Repository;

/**
 *
 * @author Jules Maurice Mulisa
 */
public class AttendanceController_Increment1 {
    private final Repository repositoryObj;
    
    /**
     * creating a new Repository from swipes file if specified by the user
     */
        
    public AttendanceController_Increment1() {
        
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
    
    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "=========");       
    }
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");
           
        Iterator it = repositoryObj.getItems().listIterator();
        while (it.hasNext()) {
            Swipe swipe = (Swipe) it.next();
            System.out.println(swipe);
        }
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
