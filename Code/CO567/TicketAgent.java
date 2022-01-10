
/**
 * Write a description of class TicketAgent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TicketAgent extends User
{
    // instance variables - replace the example below with your own
    Tickets tickets = new Tickets();
    public TicketAgent(int id)
    {
        super(id);
        Customer customer = new Customer(id);
    }
}
