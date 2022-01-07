
/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Customer
{
    DatabaseManager db = new DatabaseManager();
    InputReader reader = new InputReader();
    // instance variables - replace the example below with your own
    String cardholderName;
    String cardNumber;
    String expirationDate;
    String ccv;
    
    int billingAddressID;
    String addressLine1;
    /**
     * Constructor for objects of class Payment
     */
    public Payment(int id)
    {
        super(id);
        if(db.getData("userID", "Payment", "ID ="+ id +"")==null)
        {
            addPayment();
        }
    }

    public void addPayment()
    {
        addBillingAddress();
        billingAddressID = Integer.parseInt(db.getMaxID("BillingAddress"));
        cardholderName = reader.getString("Cardholder Name:");
        cardNumber = reader.getString("Card Number:");
        expirationDate = reader.getString("Expiration Date:");
        ccv = reader.getString("ccv:");
        db.insertDB("Payment", "userID, billingAddressID, cardholderName, cardNumber, expirationDate, ccv", id + ", " + billingAddressID + ",'" + cardholderName + "', '" + cardNumber +"', '" + expirationDate + "', '" + ccv + "'");
    }
    
    public int addBillingAddress()
    {
        String addressLine1 = reader.getString("Address Line 1:");
        String addressLine2 = reader.getString("Address Line 2:");
        String city = reader.getString("City/town:");
        String postcode = reader.getString("Postcode:");
        db.insertDB("BillingAddress", "addressline1, addressline2, city, postcode", "'" + addressLine1 + "', '" + addressLine2 +"', '" + city + "', '" + postcode + "'");
        return Integer.parseInt(db.getMaxID("BillingAddress"));
    }
}
