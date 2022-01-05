
/**
 * Write a description of class TicketAgent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TicketAgent extends User
{
    InputReader reader = new InputReader();
    DatabaseManager db = new DatabaseManager();
    // instance variables - replace the example below with your own
    
    public TicketAgent(int id)
    {
        super(id);
        Customer customer = new Customer(id);
    }
}
