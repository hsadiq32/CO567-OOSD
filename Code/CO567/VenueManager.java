
/**
 * Write a description of class VenueManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class VenueManager extends User
{
    Discounts discounts = new Discounts();
    TicketAgent ticketAgent = new TicketAgent(id);
    
    public final String[] months = 
    {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "Septemeber",
        "October",
        "November",
        "December"
    };
    /**
     * Constructor for objects of class Customer
     */
    public VenueManager(int id)
    {
        super(id);
        Venue venue = new Venue();
        Events events = new Events();
        Shows shows = new Shows();
        VenueManagerUI();
    }
    
    public void VenueManagerUI()
    {
        while(logoff = true)
        {
            System.out.println("1. Manage Events");
            System.out.println("2. Manage Users");
            System.out.println("3. Exit");
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                addEvent();
            }
            else if(option.equals("2"))
            {

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
    
    public void addEvent()
    {
        String title = reader.getString("Event title:");
        String description = reader.getString("Event desciption:");
        int year = reader.getInt("Enter year of event:", 2022, 2100);
        int month = reader.getInt("Enter month of event:", 1, 31);
        int day = reader.getInt("Enter day of event:", 1, 31);
        int startHours = reader.getInt("Enter start time hours:", 0, 23);
        int startMinutes = reader.getInt("Enter start time minutes:", 0, 59);
        int endHours = reader.getInt("Enter end time hours:", 0, 23);
        int endMinutes = reader.getInt("Enter end time minutes:", 0, 59);
        String dateTime = day + "," + month + "," + year + "," + startHours + "," + startMinutes + "," + endHours + "," + endMinutes;
        db.insertDB("Events", "title, description, dateTime","'" + title + "', '" + description +"', '" + dateTime + "'");
        while(true)
        {
            addShow(Integer.parseInt(db.getMaxID("Events")));
            String choice = reader.getInput("Add another show? (y/n)");
            if(choice.equals("n"))
            {
                break;
            }
        }
    }
    public void addShow(int eventID)
    {
        String title = reader.getString("Event title:");
        String description = reader.getString("Event desciption:");
        int maxSeats = reader.getInt("Enter max seats per customer:", 0, 500);
        int startHours = reader.getInt("Enter start time hours:", 0, 23);
        int startMinutes = reader.getInt("Enter start time minutes:", 0, 59);
        int endHours = reader.getInt("Enter end time hours:", 0, 23);
        int endMinutes = reader.getInt("Enter end time minutes:", 0, 59);
        String time = startHours + "," + startMinutes + "," + endHours + "," + endMinutes;
        db.insertDB("Shows", "eventID, title, description, dateTime", eventID + ", '" + title + "', '" + description +"', '" + time + "'");
    }
    
    public void addTicketAgent()
    {
        while(true){
            String firstName = reader.getString("First Name:");
            String lastName = reader.getString("Last Name:");
            String email = reader.getString("email:");
            String password = reader.getString("password:");
            if(db.getData("email", "Users", "email ='"+ email +"'")==null){
                db.insertDB("Users", "firstName, lastName, email, password, userType", "'" + firstName + "', '" + lastName +"', '" + email + "', '" + password + "', 1");
                int id = Integer.parseInt(db.getData("ID", "Users", "email ='"+ email +"'"));
                int commission = 1;
                int seatStartRange = 1;
                int seatEndRange = 1;
                db.insertDB("TicketAgents", "userID, commission, assignedSeats", id + "', '" + lastName +"', '" + email + "', '" + password + "', 1");
                break;
            }
            else{
                System.out.println("Email taken");
            }
        }
    }
}
