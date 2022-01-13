
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
    Venue venue;
    Events events;
    Shows shows;
    
    public final String[] mainOptions = 
    {
        "Manage Events/Shows",
        "Manage TicketAgents",
        "Manage Promotions",
        "Exit"
    };
    
    public final String[] manageEvents = 
    {
        "View Events/Shows",
        "Modify Events",
        "Modify Shows",
        "Go Back"
    };
    
    public final String[] modifyEvents = 
    {
        "Add Events",
        "Edit Events",
        "Remove Events",
        "Go Back"
    };
    
    public final String[] modifyShows = 
    {
        "Add Shows",
        "Edit Shows",
        "Remove Shows",
        "Go Back"
    };
    
    public final String[] modifyTicketAgents = 
    {
        "Add Ticket Agents",
        "Edit Ticket Agents",
        "Remove Ticket Agents",
        "Go Back"
    };
    
    public final String[] editTicketAgents = 
    {
        "Edit Commission Percentage",
        "Edit Salary",
        "Edit Assigned Seats",
        "Go Back"
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
    }
    
    public void venueManagerUI()
    {
        while(logoff = true)
        {
            reader.printOptions(mainOptions);
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                manageEvents();
            }
            else if(option.equals("2"))
            {
                modifyTicketAgents();
            }
            else if(option.equals("3"))
            {

                //promotions
            }
            else if(option.equals("4"))
            {
                break;
            }
            else
            {
                System.out.println("Choose a valid option");
            }
        }
    }
    
    public void manageEvents()
    {
        while(true)
        {
            reader.printOptions(manageEvents);
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                modifyEvents();
            }
            else if(option.equals("2"))
            {
                modifyShows();
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
    
    public void modifyEvents()
    {
        while(true)
        {
            db.selectAll("Events");
            reader.printOptions(modifyEvents);
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                addEvent();
            }
            else if(option.equals("2"))
            {
                //edit
            }
            else if(option.equals("3"))
            {
                
                //remove
            }
            else if(option.equals("4"))
            {
                break;
            }
            else
            {
                System.out.println("Choose a valid option");
            }
        }
    }
    
    
    public void modifyShows()
    {
        while(true)
        {
            db.selectAll("Shows");
            reader.printOptions(modifyShows);
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                while(true)
                {
                     db.selectAll("Events");
                     int eventID = reader.getInt("Enter event ID:");
                    if(db.getData("ID", "Events", "ID = "+ eventID)==null)
                    {
                        System.out.println("Choose a valid Event ID");
                    }
                    else{
                        addShow(eventID);
                        break;
                    }
                }
            }
            else if(option.equals("2"))
            {
                //edit show
            }
            else if(option.equals("3"))
            {
                
                //remove show
            }
            else if(option.equals("4"))
            {
                break;
            }
            else
            {
                System.out.println("Choose a valid option");
            }
        }
    }
    
    public void modifyTicketAgents()
    {
        while(true)
        {
            db.selectAll("TicketAgents");
            reader.printOptions(modifyTicketAgents);
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                addTicketAgent();
            }
            else if(option.equals("2"))
            {
                while(true)
                {
                    int ticketAgentID = reader.getInt("Enter Ticket Agent ID:");
                    if(db.getData("ID", "TicketAgents", "ID = " + ticketAgentID)!=null)
                    {
                        editTicketAgent(ticketAgentID);
                        break;
                    }
                    System.out.println("Choose a valid Ticket Agent ID");
                }
            }
            else if(option.equals("3"))
            {
                //remove ticket
            }
            else if(option.equals("4"))
            {
                break;
            }
            else
            {
                System.out.println("Choose a valid option");
            }
        }
    }
    
    public void editTicketAgent(int ticketAgentID)
    {
        int userID = Integer.parseInt(db.getData("userID", "TicketAgents", "ID =" + ticketAgentID));
        while(true)
        {
            db.selectColumns("SELECT * FROM Users WHERE ID = " + userID, db.getColumns("Users"));
            db.selectColumns("SELECT * FROM TicketAgents WHERE ID = " + ticketAgentID, db.getColumns("TicketAgents"));
            reader.printOptions(editTicketAgents);
            String option = reader.getString("Choose an option from above:");
            if(option.equals("1"))
            {
                addTicketAgent();
            }
            else if(option.equals("2"))
            {
            }
            else if(option.equals("3"))
            {
                //remove ticket
            }
            else if(option.equals("4"))
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
        int year = reader.getInt("Enter year of event:", 2022, 5000);
        int month = reader.getInt("Enter month of event:", 1, 31);
        int day = reader.getInt("Enter day of event:", 1, 31);
        String date = day + "/" + month + "/" + year;
        db.insertDB("Events", "title, description, date","'" + title + "', '" + description +"', '" + date + "'");
        int eventID = Integer.parseInt(db.getMaxID("Events"));
        addShow(eventID);
        while(true)
        {
            String choice = reader.getInput("Add another show? (y/n)");
            if(choice.equals("n"))
            {
                break;
            }
            else if(choice.equals("y"))
            {
                addShow(eventID);
            }
        }
    }
    public void addShow(int eventID)
    {   
        db.selectColumns("SELECT * FROM Shows WHERE eventID = " + eventID, db.getColumns("Shows"));
        String title = reader.getString("Show title:");
        String description = reader.getString("Show desciption:");
        int maxSeats = reader.getInt("Enter max seats per customer:", 0, 500);
        int startHours = reader.getInt("Enter start time hours:", 0, 23);
        int startMinutes = reader.getInt("Enter start time minutes:", 0, 59);
        int endHours = reader.getInt("Enter end time hours:", 0, 23);
        int endMinutes = reader.getInt("Enter end time minutes:", 0, 59);
        String time = startHours + ":" + startMinutes + "-" + endHours + ":" + endMinutes;
        db.insertDB("Shows", "eventID, title, description, time", eventID + ", '" + title + "', '" + description +"', '" + time + "'");
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
                int seatStart = 1;
                int seatEnd = 1;
                String seatRange = seatStart + "-" + seatEnd;
                db.insertDB("TicketAgents", "userID, commission, assignedSeats", id + ", " + commission +", "+  seatRange);
                break;
            }
            else{
                System.out.println("Email taken");
            }
        }
    }
}
