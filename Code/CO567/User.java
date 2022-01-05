
/**
 * Write a description of class User here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User
{
    DatabaseManager db = new DatabaseManager();
    // instance variables - replace the example below with your own
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    /**
     * Constructor for objects of class User
     */
    public User(int id)
    {
        this.id = id;
        firstName = db.getData("firstName", "Users", "ID ="+ id +"");
        lastName = db.getData("lastName", "Users", "ID ="+ id +"");
        email = db.getData("email", "Users", "ID ="+ id +"");
        password = db.getData("password", "Users", "ID ="+ id +"");
    }

    public String getFullName()
    {
        return firstName + lastName;
    }
}
