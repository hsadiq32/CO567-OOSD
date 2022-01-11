
/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment
{
    // instance variables - replace the example below with your own
    int userID;
    int billingcardID;
    int billingAddressID;
    BillingCard card;
    BillingAddress address;
    /**
     * Constructor for objects of class Payment
     */
    public Payment(int userID)
    {
        BillingAddress address = new BillingAddress();
        BillingCard card = new BillingCard(userID);
        card.ccvget();
    }
}
