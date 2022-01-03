
/**
 * Write a description of class VenueManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class VenueManager
{
    InputReader reader = new InputReader();
    DatabaseManager db = new DatabaseManager();
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
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
        this.id = id;
        firstName = db.getData("firstName", "Users", "ID ="+ id +"");
        lastName = db.getData("lastName", "Users", "ID ="+ id +"");
        email = db.getData("email", "Users", "ID ="+ id +"");
        password = db.getData("password", "Users", "ID ="+ id +"");
        if(db.getData("userID", "Payment", "ID ="+ id +"")==null)
        {
            addEvent();
        }
    }
    
    public void addEvent()
    {
        String title = reader.getString("Event title:");
        String description = reader.getString("Event desciption:");
        int startHours = reader.getInt("Enter start time hours:", 0, 23);
        int startMinutes = reader.getInt("Enter start time minutes:", 0, 59);
        int endHours = reader.getInt("Enter end time hours:", 0, 23);
        int endMinutes = reader.getInt("Enter end time minutes:", 0, 59);
        int day = reader.getInt("Enter day of event:", 1, 31);
        int month = reader.getInt("Enter month of event:", 1, 31);
        int year = reader.getInt("Enter year of event:", 2021, 2100);
        String dateTime = day + "," + month + "," + year + "," + startHours + "," + startMinutes + "," + endHours + "," + endMinutes;
        db.insertDB("Events", "title, description, dateTime","'" + title + "', '" + description +"', '" + dateTime + "'");
        while(true)
        {
            addShow(Integer.parseInt(db.getMaxID("Events")));
            String choice = reader.getString("Add another show? (y/n)");
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
                break;
            }
            else{
                System.out.println("Email taken");
            }
        }
    }
}
