
/**
 * Write a description of class TicketAgent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TicketAgent
{
    InputReader reader = new InputReader();
    DatabaseManager db = new DatabaseManager();
    // instance variables - replace the example below with your own
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    
    public TicketAgent(int id)
    {
        this.id = id;
        firstName = db.getData("firstName", "Users", "ID ="+ id +"");
        lastName = db.getData("lastName", "Users", "ID ="+ id +"");
        email = db.getData("email", "Users", "ID ="+ id +"");
        password = db.getData("password", "Users", "ID ="+ id +"");
        if(db.getData("userID", "Payment", "ID ="+ id +"")==null)
        {
            
        }
    }
}
