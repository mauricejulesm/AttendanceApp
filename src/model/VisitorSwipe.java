package model;

import java.util.Calendar;

/**
 *
 * @author Jules Maurice Mulisa
 * @version 1.0
 */
public class VisitorSwipe extends Swipe {
    
    static final char EOLN='\n';       
    static final String QUOTE="\"";
    
    /**
     *the name of a visitor
     */
    protected String visitorName;

    /**
     *the company a visitor is from
     */
    protected String visitorCompany;

    /**
     *calls the super class and initializes default values to the field variables
     */
    public VisitorSwipe() {
        super();
        this.visitorName = "Unknown";
        this.visitorCompany = "Unknown";        
    }

    /**
     *
     * @param cardId
     * @param room
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(String cardId, String room, String visitorName, String visitorCompany) {
        super(cardId, room);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }

    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(int swipeId, String cardId, String room, Calendar swipeDateTime, String visitorName, String visitorCompany) {
        super(swipeId, cardId, room, swipeDateTime);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }
    
    // Methods required: getters, setters  
    
    /**
     * @return the visitorName
     */
    private String getVisitorName(){
        return this.visitorName;
    }
    /**
     * @return the visitorCompany
     */
    public String getVisitorCompany() {
        return this.visitorCompany;
    }
    
    /**
     * @param visitorName
     */
    private void setVisitorName(String visitorName){
        this.visitorName = visitorName;
    }
    /**
     * @param visitorCompany
     */
     private void setVisitorCompany(String visitorCompany){
        this.visitorCompany = visitorCompany;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Name: " + this.visitorName +  " - Company: " + this.visitorCompany;
    }
    
    public String toString(char delimiter) {
        final String QUOTE = "\"";
        final String EOLN = "\n";
        String output =  Integer.toString(this.id) + delimiter +QUOTE + this.cardId+QUOTE+
                delimiter+QUOTE+ this.room+QUOTE+ delimiter+QUOTE+formatSwipeDateTime(this.swipeDateTime)+
                QUOTE+delimiter+QUOTE+this.visitorName+QUOTE+delimiter+QUOTE+this.visitorCompany+QUOTE;
                         
        output += EOLN;
        return output;
    }
}
