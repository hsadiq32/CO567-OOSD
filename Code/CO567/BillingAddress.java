
/**
 * Write a description of class BillingAddress here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BillingAddress
{
    DatabaseManager db = new DatabaseManager();
    // instance variables - replace the example below with your own
    String addressLine1;
    String addressLine2;
    String city;
    String postcode;
    /**
     * Constructor for objects of class BillingAddress
     */
    public BillingAddress()
    {
        
    }

    public int addBillingAddress(String addressLine1, String addressLine2, String city, String postcode)
    {
        db.insertDB("BillingAddress", "addressline1, addressline2, city, postcode", "'" + addressLine1 + "', '" + addressLine2 +"', '" + city + "', '" + postcode + "'");
        return Integer.parseInt(db.getMaxID("BillingAddress"));
    }
}
