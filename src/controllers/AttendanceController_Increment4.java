package controllers;

import helpers.InputHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author Jules Maurice Mulisa
 */
public class AttendanceController_Increment4 {
    private final Repository repositoryObj;
    
    /**
     * creating a new Repository from swipes file if specified by the user
     */
        
    public AttendanceController_Increment4() {
        
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
           
        Iterator it = repositoryObj.getItems().listIterator();
        while (it.hasNext()) {
            Swipe swipe = (Swipe) it.next();
            System.out.println(swipe);
        }
    }      
      

    private void listSwipesByCardIdOrderedByDateTime() {  
        InputHelper inputHelper = new InputHelper();
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
        
        String cardId = inputHelper.readString("Provide A Card Id");
        List<Swipe> swipesToSort = new ArrayList<>();
        
        Iterator it = repositoryObj.getItems().listIterator();
        while (it.hasNext()) {
            Swipe swipe = (Swipe) it.next();
            if (swipe.getCardId().equals(cardId)){
                swipesToSort.add(swipe);
            }
        }
        Collections.sort(swipesToSort, Swipe.SwipeDateTimeComparator);
        for( Swipe swipes:swipesToSort){
            
            System.out.println(swipes);
        }
    }   
    
    private void listSwipeStatistics() {
        InputHelper inputHelper = new InputHelper();
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "=========================");
        String room = inputHelper.readString("Provide A Room Id");
        Iterator it = repositoryObj.getItems().listIterator();
        int counter = 0;
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
        while (it.hasNext()) {
            Swipe swipe = (Swipe) it.next();
            if (swipe.getRoom().equals(room)){
                counter++;
                String retrievedDate = Swipe.formatSwipeDateTime(swipe.getSwipeDateTime());
                try {
                    Date date = formatDate.parse(retrievedDate);
                    dates.add(date);
                } catch (ParseException ex) {
                    Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(swipe);
            }
        }
        if(counter != 0){
            String lastSwipeDateTime = formatDate.format(Collections.max(dates));
            System.out.println("\nRoom: "+room+" has: "+counter+" Swipes");
            System.out.println("Last Swipe Date Time : "+lastSwipeDateTime);
        } else {
            System.out.format("\033[31m%s\033[0m%n", "Sorry "+room+ " Rooom Doesn't Exist,Provide Correct Room Id");  
        }
    }
    
}
