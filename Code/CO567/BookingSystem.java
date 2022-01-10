
/**
 * Write a description of class Bookings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BookingSystem
{
    // instance variables - replace the example below with your own
    Payment payment = new Payment(3);
    Shows shows = new Shows();
    Events events = new Events();
    Discounts discounts = new Discounts();
    Tickets tickets = new Tickets();
    Venue venue = new Venue();
    DatabaseManager db = new DatabaseManager();
    public int bookingID;
    public int userID;
    public int showID;
    public int ticketAgentID;
    public int promotionID;
    public int salePrice;
    public String dateTime;
    public boolean bought;

    /**
     * Constructor for objects of class Bookings
     */
    public BookingSystem()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void sampleMethod()
    {

    }
}
