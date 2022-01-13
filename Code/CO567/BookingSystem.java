import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Bookings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BookingSystem
{
    // instance variables - replace the example below with your own
    InputReader reader = new InputReader();
    Payment payment = new Payment(3);
    Shows shows = new Shows();
    Events events = new Events();
    Discounts discounts = new Discounts();
    Tickets tickets = new Tickets();
    Venue venue = new Venue();
    DatabaseManager db = new DatabaseManager();
    public int bookingID;
    public int userID;
    public int eventID;
    public int showID;
    public int ticketAgentID;
    public int promotionID;
    public int salePrice;
    public String dateTime;
    public boolean bought;

    public final String[] showColumns ={ "ID","title", "description", "maxSeats", "time"};
    
    /**
     * Constructor for objects of class Bookings
     */
    public BookingSystem(int userID)
    {
        this.userID = userID;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void bookingSystemUI()
    {
        holdTicket(viewShows(viewEvents()));
    }
    
    public int viewEvents()
    {   
        while(true)
        {
            db.selectAll("Events");
            eventID =reader.getInt("Enter Event ID:");
            if(db.getData("ID", "Events", "ID = "+ eventID)==null)
            {
                System.out.println("Choose a valid Event ID:");
            }
            else
            {
                break;
            }
        }
        return eventID;
    }
    
    public int viewShows(int eventID)
    {   
        ArrayList<String> data = new ArrayList<String>();
        data.add("ID");
        data.add("title");
        data.add("description");
        data.add("maxSeats");
        data.add("time");
        while(true)
        {
            System.out.println("Event Title: " + db.getData("title", "Events", "ID = "+ eventID));
            System.out.println("Event Description: " + db.getData("description", "Events", "ID = "+ eventID));
            db.selectColumns("SELECT * FROM Shows WHERE eventID = " + eventID, data);
            showID =reader.getInt("Enter Show ID");
            if(db.getData("ID", "Shows", "ID = "+ showID)==null)
            {
                System.out.println("Choose a valid Show ID");
            }
            else
            {
                break;
            }
        }
        return showID;
    }
    
    public void holdTicket(int showID)
    {   
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        Date date = new Date();
        db.insertDB("Bookings", "userID, showID, salePrice, dateTime, bought", userID + ", " + showID +", 5, '" + dateFormat.format(date) + "', 0");
    }
}
