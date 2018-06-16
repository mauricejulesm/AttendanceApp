/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this arrayOfStringslate file, choose Tools | Templates
 * and open the arrayOfStringslate in the editor.
 */
package daos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author Jules Maurice Mulisa
 */
public class DAOImpl implements DAOInterface{
   static final char DELIMITER=',';   

    @Override
    public Repository load(String filename) {
        
        Repository repositoryObj = new Repository();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { 
            String[] arrayOfStrings;
            String lineOfStrings = br.readLine();
            
            while(lineOfStrings!=null){
                arrayOfStrings=lineOfStrings.split(Character.toString(DELIMITER));
                int length = arrayOfStrings.length;
                boolean visitor = length>4;
                /*
                *  visitor swipes will be of length>4
                */
                if(!visitor){
                    int swipeId = Integer.parseInt(arrayOfStrings[0]);
                    String cardId = stripQuotes(arrayOfStrings[1]);
                    String room = stripQuotes(arrayOfStrings[2]);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
                    String strDate= stripQuotes(arrayOfStrings[3]);
                    Calendar swipeDateTime = Calendar.getInstance();
                    try {
                        Date date = sdf.parse(strDate);
                        swipeDateTime.setTime(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //store read data in Swipe object
                    Swipe swipe = new Swipe(swipeId, cardId, room, swipeDateTime);
                    repositoryObj.add(swipe);
                }else{
                    int swipeId = Integer.parseInt(arrayOfStrings[0]);
                    String cardId = stripQuotes(arrayOfStrings[1]);
                    String room = stripQuotes(arrayOfStrings[2]);
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
                    String strDate= stripQuotes(arrayOfStrings[3]);
                    Calendar swipeDateTime = Calendar.getInstance();
                    try {
                        Date date = dateFormat.parse(strDate);
                        swipeDateTime.setTime(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String visitorName = stripQuotes(arrayOfStrings[4]);
                    String visitorCompany = stripQuotes(arrayOfStrings[5]);
                    
                    //store read data in VisitorSwipe object
                    VisitorSwipe vswipe = new VisitorSwipe(swipeId, cardId, room, swipeDateTime, visitorName, visitorCompany);
                    repositoryObj.add(vswipe);
                }
                 
                //read the next line
                lineOfStrings = br.readLine();                
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repositoryObj;
    }
    // removing the quotes on the strings
    private String stripQuotes(String str) {
        return str.substring(1, str.length()-1);
    }
    // last action for persisting swipes to the file
    @Override
    public void store(String filename, Repository repositoryObj) {
        try (PrintWriter output = new PrintWriter(filename)) {
            output.print(repositoryObj.toString(DELIMITER));
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
