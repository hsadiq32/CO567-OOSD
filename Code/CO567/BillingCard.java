
/**
 * Write a description of class BillingCard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BillingCard
{
    DatabaseManager db = new DatabaseManager();
    BillingAddress address;
    // instance variables - replace the example below with your own
    String cardholderName;
    String cardNumber;
    String expirationDate;
    String ccv;
    
    /**
     * Constructor for objects of class BillingCard
     */
    public BillingCard(int id)
    {
        if(db.getData("userID", "Payment", "ID ="+ id +"")==null)
        {
            BillingAddress address = new BillingAddress();
        }
    }
    
    public void addBillingCard(int billingAddressID, String cardholderName, String cardNumber, String expirationDate, String ccv)
    {
        //db.insertDB("Payment", "userID, billingAddressID, cardholderName, cardNumber, expirationDate, ccv", id + ", " + billingAddressID + ",'" + cardholderName + "', '" + cardNumber +"', '" + expirationDate + "', '" + ccv + "'");
    }
    
    public void ccvget()
    {
        System.out.println(ccv);
    }
}
