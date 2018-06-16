package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

/**
 *
 * @author Jules Maurice Mulisa
 * @version 1.0
 */
public class Swipe implements Comparator<Calendar>{

    /**
     *the unique id of each swipe 
     */
    protected final Integer id;

    /**
     *the id of a card swiped
     */
    public String cardId;

    /**
     *the room name where the card is swiped
     */
    protected String room;
    /**
     *the swipeDateTime the datetime when the card was swiped
     */
    protected final Calendar swipeDateTime;
    private static int lastSwipeIdUsed = 0;
    static final char EOLN='\n';       
    static final String QUOTE="\"";    
    
    public static Comparator<Swipe> SwipeDateTimeComparator = new Comparator<Swipe>() {
        @Override
        public int compare(Swipe s1, Swipe s2) {
            return s1.getSwipeDateTime().compareTo(s2.getSwipeDateTime());
        }
    };
    
    /**
     *the constructor of class Swipe
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }
    
    /**
     *
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeDateTime = getNow();
    } 
    
    
    /**
     *
     * @param swipeId           the id of each swipe
     * @param cardId            the id of each card
     * @param room              the room on which the swipe card is used
     * @param swipeDateTime     the date at which the card has been swiped
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }   
    
     
    /*
    * retrieving the current datetime
    */
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    // Methods required: getters, setters, hashCode, equals, compareTo, comparator
 
    /**
     * @return the cardId
     */
    public String getCardId() {
        return this.cardId;
    }
      /**
     * @return the Room
     */
    public String getRoom() {
        return this.room;
    }
      /**
     * @return the datetime
     */
    public Calendar getSwipeDateTime() {
        return this.swipeDateTime;
    }
    
    // setters
    
    /**
     * @param cardId
     */
    public void setcardId(String cardId) {
       this.cardId = cardId ;
    }
      /**
     * @param room
     */
    public void setRoom(String room) {
       this.room = room;
    }
     
    
    /**
     * Overriding and customizing equals() method
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) 
            return false;
        if (!(obj instanceof Swipe))
        if (obj == this)
            return true;
    return this.getId() == ((Swipe) obj).getId();

    }
    
    /**
     * Overriding and customizing hashCode() method
     */
    @Override
    public int hashCode() {
        return id;
    }
    
    private Integer compareTo(Swipe compareSwipe){
        return id;
    }
    
    /**
     *
     * @param calendar
     * @return the formatted date
     */
        
    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }    

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Swipe Id: " + this.id + " - Card Id: " + this.cardId +            
                " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }

    public String toString(char delimiter) {
        final String QUOTE = "\"";
        final String EOLN = "\n";
        String output =  Integer.toString(this.id) + delimiter +
                         QUOTE + this.cardId + QUOTE + delimiter+ 
                QUOTE+ this.room+QUOTE+ delimiter+QUOTE+
                formatSwipeDateTime(this.swipeDateTime)+QUOTE;
        
        output += EOLN;       
        return output;
    }
    
    @Override
    public int compare(Calendar cal1, Calendar cal2) {
       return cal1.compareTo(cal2);
    }
    
}
