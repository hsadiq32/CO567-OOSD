import java.util.ArrayList;
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer extends User
{
    InputReader reader = new InputReader();
    DatabaseManager db = new DatabaseManager();
    /**
     * Constructor for objects of class Customer
     */
    public Customer(int id)
    {
        super(id);
        System.out.println(super.getFullName() + "," + super.email);
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
        db.insertDB("Payment", "userID, billingAddressID, cardholderName, cardNumber, expirationDate, ccv", id + ", " + billingAddressID + ",'" + cardholderName + "', '" + cardNumber +"', '" + expirationDate + "', '" + ccv + "'");
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
