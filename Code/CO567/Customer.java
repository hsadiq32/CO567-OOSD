import java.util.ArrayList;
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    InputReader reader = new InputReader();
    DatabaseManager db = new DatabaseManager();
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    /**
     * Constructor for objects of class Customer
     */
    public Customer(int id)
    {
        this.id = id;
        firstName = db.getData("firstName", "Users", "ID ="+ id +"");
        lastName = db.getData("lastName", "Users", "ID ="+ id +"");
        email = db.getData("email", "Users", "ID ="+ id +"");
        password = db.getData("password", "Users", "ID ="+ id +"");
        if(db.getData("userID", "Payment", "ID ="+ id +"")==null)
        {
            addPayment();
        }
        customerUI();
    }
    
    public void customerUI()
    {
        db.selectAll("Events");
        
        int eventid = reader.getInt("Choose Event ID:");
    }
    
    public void addPayment()
    {
        addBillingAddress();
        int billingAddressID = Integer.parseInt(db.getMaxID("BillingAddress"));
        String cardholderName = reader.getString("Cardholder Name:");
        String cardNumber = reader.getString("Card Number:");
        String expirationDate = reader.getString("Expiration Date:");
        String ccv = reader.getString("ccv:");
        db.insertDB("Payment", "userID, billingAddressID, cardholderName, cardNumber, expirationDate, ccv", id + ", " + billingAddressID + ",'" + firstName + "', '" + lastName +"', '" + email + "', '" + password + "'");
    }
    
    public void addBillingAddress()
    {
        String addressLine1 = reader.getString("Address Line 1:");
        String addressLine2 = reader.getString("Address Line 2:");
        String city = reader.getString("City/town:");
        String postcode = reader.getString("Postcode:");
        db.insertDB("BillingAddress", "addressline1, addressline2, city, postcode", "'" + addressLine1 + "', '" + addressLine2 +"', '" + city + "', '" + postcode + "'");
    }
}
