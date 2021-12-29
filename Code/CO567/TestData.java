
/**
 * Write a description of class Venue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestData{
    Customer2 customer = new Customer2("mark","smith","smith@gmail.com","password", "10", "New Lane", "London", "GD56S2");
    // instance variables - replace the example below with your own
    private int x;


    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void sampleMethod()
    {
        System.out.println(customer.firstName);
        System.out.println(customer.lastName);
        System.out.println(customer.email);
        System.out.println(customer.password);
        System.out.println(customer.address.city);
        System.out.println(customer.address.addressLine1);
        System.out.println(customer.address.addressLine2);
        System.out.println(customer.address.postcode);
        customer.address.postcode = "kjbhbhb";
        System.out.println(customer.address.postcode);
    }
}
