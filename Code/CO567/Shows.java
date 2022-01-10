
/**
 * Write a description of class Shows here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shows
{
    DatabaseManager db = new DatabaseManager();
    
    // instance variables - replace the example below with your own
    public int showID;
    public int eventID;
    public String title;
    public String description;
    public int maxSeats;
    public String time;
    
    // pricing
    public int tier1;
    public int tier2;
    public int tier3;
    
    

    /**
     * Constructor for objects of class Shows
     */
    public Shows()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod()
    {
        // put your code here
        return 1;
    }
}
