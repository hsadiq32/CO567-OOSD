
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
    private int x;

    /**
     * Constructor for objects of class Bookings
     */
    public BookingSystem()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
