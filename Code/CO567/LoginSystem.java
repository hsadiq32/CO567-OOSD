
/**
 * Write a description of class OTP here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LoginSystem
{
    InputReader reader = new InputReader();
    DatabaseManager db = new DatabaseManager();
    public LoginSystem()
    {
        db.createTables();
        if(db.getData("userType", "Users", "userType =2")==null){
            db.insertDB("Users", "firstName, lastName, email, password, userType", "'John', 'Smith', '1', '2', 2");
        }
        startMenu();
    }
    public void startMenu()
    {
        while(true)
        {
            System.out.println("1. Sign up");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                signup();
            }
            else if(option.equals("2"))
            {
                login();
            }
            else if(option.equals("3"))
            {
                break;
            }
            else
            {
                System.out.println("Choose a valid option");
            }
        }
    }

    public void signup()
    {
        while(true){
            String firstName = reader.getString("First Name:");
            String lastName = reader.getString("Last Name:");
            String email = reader.getString("email:");
            String password = reader.getString("password:");
            if(db.getData("email", "Users", "email ='"+ email +"'")==null){
                db.insertDB("Users", "firstName, lastName, email, password", "'" + firstName + "', '" + lastName +"', '" + email + "', '" + password + "'");
                accountSelector(email);
                break;
            }
            else{
                System.out.println("Email taken");
            }
        }
    }
    
    public void login()
    {
        while(true){
            String email = reader.getString("email:");
            String password = reader.getString("password:");
            String dbemail = db.getData("email", "Users", "email ='"+ email +"'");
            String dbpass = db.getData("password", "Users", "email ='"+ email +"'");
            if(dbemail != null && dbpass != null)
            {
                if(dbemail.equals(email) && dbpass.equals(password))
                {
                    accountSelector(email);
                    break;
                }
                else
                {
                    System.out.println("Wrong password");
                }
            }
            else
            {
                System.out.println("Email not found");
            }
        }
    }
    
    public void accountSelector(String email)
    {
        int id = Integer.parseInt(db.getData("ID", "Users", "email ='"+ email +"'"));
        int accountType = Integer.parseInt(db.getData("userType", "Users", "ID ="+ id +""));
        if(accountType == 0)
        {
            System.out.println("customer");
            Customer customer = new Customer(id);
        }
        else if(accountType == 1)
        {
            System.out.println("ticketagent");
            TicketAgent ticketAgent = new TicketAgent(id);
        }
        else if(accountType == 2)
        {
            System.out.println("venuemanager");
            VenueManager venueManager = new VenueManager(id);
        }
        else
        {
            System.out.println("error");
        }
    }
}
