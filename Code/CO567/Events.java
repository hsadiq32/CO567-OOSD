
/**
 * Write a description of class Events here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Events
{
    DatabaseManager db = new DatabaseManager();
    Shows shows;
    // instance variables - replace the example below with your own
    public int eventID;
    public String title;
    public String description;
    public String date;

    /**
     * Constructor for objects of class Events
     */
    public Events()
    {
        Shows shows = new Shows();
    }
    
    
}
