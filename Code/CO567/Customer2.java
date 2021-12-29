
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer2
{
    BillingAddress address = new BillingAddress();
    // instance variables
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    
    /**
     * Constructor for objects of class Customer
     */
    public Customer2(String firstName, String lastName, String email, String password, String addressLine1, String addressLine2, String city, String postcode)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        address.addressLine1 = addressLine1;
        address.addressLine2 = addressLine2;
        address.city = city;
        address.postcode = postcode;
    }
    
    public void CustomerUI()
    {
        System.out.println(address.city);
    }
}
