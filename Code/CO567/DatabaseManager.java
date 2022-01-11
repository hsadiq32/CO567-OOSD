import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;
/**
 * Write a description of class s here.
 *
 * @author Haroon Sadiq
 * @version 0.1
 */
public class DatabaseManager
{
    public static Connection c = null;
    public static Statement stmt = null;
    public final static String usersTable = "CREATE TABLE IF NOT EXISTS Users" +
        "(ID INTEGER PRIMARY KEY," +
        " firstName STRING NOT NULL," + 
        " lastName STRING NOT NULL," +
        " email STRING NOT NULL," + 
        " password STRING NOT NULL, " +
        " userType INTEGER DEFAULT 0)";
        
    public final static String billingAddressTable = "CREATE TABLE IF NOT EXISTS BillingAddress" +
        "(ID INTEGER PRIMARY KEY," +
        " addressLine1 STRING NOT NULL," + 
        " addressLine2 STRING NOT NULL," +
        " city STRING NOT NULL," + 
        " postcode STRING NOT NULL)";
        
    public final static String paymentTable = "CREATE TABLE IF NOT EXISTS Payment" +
        "(ID INTEGER PRIMARY KEY," +
        " userID INTEGER NOT NULL, " +
        " billingAddressID INTEGER NOT NULL, " +
        " cardholderName STRING NOT NULL," + 
        " cardNumber STRING NOT NULL," +
        " expirationDate STRING NOT NULL," + 
        " ccv STRING NOT NULL," + 
        " FOREIGN KEY(userID) REFERENCES Users(ID), " +
        " FOREIGN KEY(billingAddressID) REFERENCES BillingAddress(ID))";
        
    public final static String eventsTable = "CREATE TABLE IF NOT EXISTS Events" +
        "(ID INTEGER PRIMARY KEY," +
        " title STRING NOT NULL," + 
        " description STRING NOT NULL," +
        " date STRING NOT NULL)";
        
    public final static String showsTable = "CREATE TABLE IF NOT EXISTS Shows" +
        "(ID INTEGER PRIMARY KEY," +
        " eventID INTEGER NOT NULL," + 
        " title STRING NOT NULL," + 
        " description STRING," +
        " maxSeats INTEGER DEFAULT 10," +
        " time STRING NOT NULL, " +
        " FOREIGN KEY(eventID) REFERENCES Events(ID))";

        
    public final static String concessionDiscountsTable = "CREATE TABLE IF NOT EXISTS ConcessionDiscounts" +
        "(ID INTEGER PRIMARY KEY," +
        " child INTEGER DEFAULT 0.5," + 
        " student INTEGER DEFAULT 0.8," + 
        " senior INTEGER DEFAULT 0.75)";
        
        
    public final static String seatPricingTable = "CREATE TABLE IF NOT EXISTS SeatPricing" +
        "(ID INTEGER PRIMARY KEY," +
        " showID INTEGER NOT NULL," + 
        " concessionDiscountID INTEGER NOT NULL," +
        " tier1 INTEGER DEFAULT 40," + 
        " tier2 INTEGER DEFAULT 20," + 
        " tier3 INTEGER DEFAULT 10," + 
        " FOREIGN KEY(concessionDiscountID) REFERENCES ConcessionDiscounts(ID), " +
        " FOREIGN KEY(showID) REFERENCES Shows(ID))";
        
    public final static String promotionsTable = "CREATE TABLE IF NOT EXISTS Promotions" +
        "(ID INTEGER PRIMARY KEY," +
        " code STRING NOT NULL," + 
        " discount STRING NOT NULL," + 
        " expiry STRING NOT NULL," +
        " description STRING NOT NULL)";
    
    public final static String ticketAgentsTable = "CREATE TABLE IF NOT EXISTS TicketAgents" +
        "(ID INTEGER PRIMARY KEY," +
        " userID INTEGER NOT NULL," + 
        " commission INTEGER NOT NULL," +
        " salary INTEGER DEFAULT 0," +
        " assignedSeats STRING NOT NULL," +
        " FOREIGN KEY(userID) REFERENCES Users(ID))";
        
    public final static String bookingsTable = "CREATE TABLE IF NOT EXISTS Bookings" +
        "(ID INTEGER PRIMARY KEY," +
        " userID INTEGER NOT NULL," +
        " showID INTEGER NOT NULL," + 
        " ticketAgentID INTEGER," + 
        " promotionID INTEGER," + 
        " salePrice INTEGER," +
        " dateTime STRING," +
        " bought BOOLEAN DEFAULT 0," +
        " FOREIGN KEY(showID) REFERENCES Shows(ID), " +
        " FOREIGN KEY(ticketAgentID) REFERENCES TicketAgents(ID), " +
        " FOREIGN KEY(promotionID) REFERENCES Promotions(ID), " +
        " FOREIGN KEY(userID) REFERENCES Users(ID))";

    public final static String[] sqlStatement = 
    {
        usersTable,
        billingAddressTable,
        paymentTable,
        eventsTable,
        showsTable,
        concessionDiscountsTable,
        seatPricingTable,
        promotionsTable,
        ticketAgentsTable,
        bookingsTable
    };
    
    public static Connection connect() throws Exception {
    
        if (c == null) 
        {
            c = DriverManager.getConnection("jdbc:sqlite:ProgramData/Database.bcpa");
        } else 
        {
            c.close();
            c = DriverManager.getConnection("jdbc:sqlite:ProgramData/Database.bcpa");
        }
        return c;
    }
    
    public static void connectDB(String folder, String filename, boolean autoCommit)
    {
        File f = new File(folder); 
        if (f.mkdir()) {
            System.out.println("Directory is created"); 
        } 
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + folder + "/" + filename + ".bcpa");
            stmt = c.createStatement();
            c.setAutoCommit(true);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Opened database successfully");
    }

    public void createTables()
    {
        for(String i : sqlStatement)
        {
            try {
                connectDB("ProgramData", "Database", true);
                stmt.executeUpdate(i);
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            //System.out.println("Table created successfully");
        }
    }
    
    public static void insertDB(String table, String columns, String data)
    {
        try 
        { 
            connectDB("ProgramData", "Database", true);
            stmt = c.createStatement();
            String sql = "INSERT INTO " + table + " (" + columns + ") " +
                "VALUES (" + data + ");"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
        public static void findDB(String table, String column)
    {
        try {
            connectDB("ProgramData", "Database", true);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT " + column + " FROM " + table + ";" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String  message = rs.getString("message");
                String date_added = rs.getString("date_added");
                System.out.println( "ID : " + id );
                System.out.println( "Name : " + name );
                System.out.println( "Message : " + message );
                System.out.println( "Date Added : " + date_added );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");  
    }
    
    public void selectAll(String table, String column)
    {
        String sql = "SELECT id, name, capacity FROM warehouses";
        
        try
        {
            connectDB("ProgramData", "Database", true);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + column + " FROM " + table);
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static String getData(String sqlSelect, String sqlFrom, String sqlWhere)
    {
        String sql = "SELECT "+ sqlSelect +" FROM " + sqlFrom +" WHERE " + sqlWhere;
        String data = null;
        try{
            connectDB("ProgramData", "Database", true);
            stmt = c.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            data = rs.getString(sqlSelect);
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    public static ArrayList<String> getColumns(String sqlFrom)
    {
        String sql = "SELECT * FROM " + sqlFrom;
        ArrayList<String> data = new ArrayList<String>();
        try{
            connectDB("ProgramData", "Database", true);
            stmt = c.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount(); 
            for(int i = 1; i<=count; i++) 
            {
                data.add(rsMetaData.getColumnName(i));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    public static void selectAll(String sqlFrom)
    {
        selectColumns(sqlFrom, getColumns(sqlFrom));
    }
    
    public static void selectColumns(String sqlFrom, ArrayList<String> columns)
    {
        String sql = sqlFrom;
        if(!sqlFrom.contains(" "))
        {
            sql = "SELECT * FROM " + sqlFrom;
        }
        
        try{
            connectDB("ProgramData", "Database", true);
            stmt = c.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount(); 
            String rowData = "| ";
            String underRowData = "";
            for(int i = 0; i<columns.size(); i++) 
            {
                //System.out.println(rsMetaData.getColumnName(i));
                rowData = rowData + spacer(columns.get(i)) + " | ";
            }
            for(int i = 2; i<=rowData.length(); i++) 
            {
                underRowData = underRowData + "-";
            }
            System.out.println(underRowData);
            System.out.println(rowData);
            System.out.println(underRowData);
            rowData = "| ";
            // loop through the result set
            while (rs.next()) {
                for(int i = 0; i<columns.size(); i++) 
                {
                    rowData = rowData + spacer(rs.getString(columns.get(i))) + " | ";
                }
                System.out.println(rowData);
                rowData = "| ";
            }
            System.out.println(underRowData);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String spacer(String data)
    {
        int add = 20 - data.length();
        for(int i = 1; i<=add; i++) 
        {
            data = data + " ";
        }
        return data;
    }
    
    public static String getMaxID(String sqlFrom)
    {
        String sql = "SELECT ID FROM " + sqlFrom +" ORDER BY ID DESC LIMIT 1";
        String data = null;
        try{
            connectDB("ProgramData", "Database", true);
            stmt = c.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            data = rs.getString("ID");
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}