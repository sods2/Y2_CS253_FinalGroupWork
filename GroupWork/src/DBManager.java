import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager implements DBConnection {

    private final String URL = "jdbc:postgresql://devweb2020.cis.strath.ac.uk:5432/cs253";

    Connection con;
    ResultSet result;
    PreparedStatement pstmt;
    static String name;
    static String sUserPwd;

    String arg[];

    /**
     * connecting to the database
     */
    @Override
    public boolean connect(String args[]) throws SQLException {
        arg = args;
        if (getConnection(args)) {
            System.out.println("Connection established\n");
            return true;
        }
        return false;
    }

    /**
     *  Calling the closing connection
     */
    @Override
    public void close_connection() {
        closeConnection();
    }

    /**
     *  we check if the arguments are present and then we connect to the database
     *
     *  we throw exception istead of using a try here to better manage to exceptions
     *
     *  se auto commit is set to true so that the changes will get committed
     */
    private boolean getConnection(String args[]) throws SQLException {
        //checking if arguments are present in the run config
        if (args.length == 2) {
            name = args[0];
            sUserPwd = args[1];

            // Create a new JDBC connection. This is our session.
            con = DriverManager.getConnection(URL, name, sUserPwd);
            //if set to false we'll need to commit explicitly
            con.setAutoCommit(true);
            return true;
        } else {
            System.out.println("Wrong quantity of arguments");
            new Message().usage();
            return false;
        }
    }

    /**
     *  closing the connection to the database
     */
    public void closeConnection() {
        try {
            con.close();
            System.out.println("Connection close successfully");
        } catch (Exception e) {
            System.err.println("System Exception in closeConnection: " + e);
        }
    }

    /**
     *  getting the max number of row we want to display
     *
     *  running the select query and printing all the information gathered form the database
     */
    public void listUser(int iMaxNumRows) {
        StringBuilder retVal = new StringBuilder("NAME\tSURNAME\t\tDOB\n");

        List<String> NAME = new ArrayList<>();
        List<String> SURNAME = new ArrayList<>();
        List<Date> DOB = new ArrayList<>();

        try {
            pstmt = con.prepareStatement("SELECT name, surname, dob FROM g_user ORDER BY name");
            result = pstmt.executeQuery();

            int i = 0;
            //run while next is true
            while (result.next()) {
                NAME.add(i, result.getString("name"));
                SURNAME.add(i, result.getString("surname"));
                DOB.add(i, result.getDate("dob"));
                //appending only up to the max number of rows the user choose to display
                if (i < iMaxNumRows) {
                    retVal.append(NAME.get(i)).append("\t").append(SURNAME.get(i)).append("\t").append(DOB.get(i)).append("\n");
                }
                i++;
            }
            pstmt.close();
        } catch (Exception e) {
            System.err.println("System Exception in User table " + e + "\nPlease Try Again");
            //giving time to the program to write all messages otherwise the exception will be displayed on the wrong position
            try {
                new Thread().sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Error while waiting");
            }
        }
        System.out.println(retVal.toString());
    }

    /**
     *  inserting the value that the user inserted (name, surname, dob) into the table user
     */
    public void insert(String name, String surname, String dob) {
        try {
            //in case of previous run with error we'll need to restart the connection
            //so if the connection was closed we'll try opening it again
            if (!isClosed()){
                connect(arg);
            }

            int id = getnewID();

            //creating the sql query
            String sqlStatement = get_insert(id, name, surname, dob);

            pstmt = con.prepareStatement(sqlStatement);
            pstmt.executeUpdate();

            System.out.println("Database updated successfully ");
        } catch (SQLException e) {
            System.err.println("System Exception while executing insert in User table " + e + "\nPlease Restart the application and Try Again");
            //giving time to the program to write all messages otherwise the exception will be displayed on the wrong position
            //also if an error occur we close the connection so that we will not have a problem with it if trying to query again
            try {
                new Thread().sleep(100);
                if (isClosed()){
                    close_connection();
                }
            } catch (InterruptedException ex) {
                System.out.println("Error while waiting");
            } catch (SQLException ex) {
                System.out.println("Connection issue");
            }
        }
    }

    /**
     *  constructing the query for the insert
     */
    private String get_insert(int id, String name, String surname, String dob) {
        return "INSERT INTO g_user values('"
                + id + "', '"
                + name + "', '"
                + surname + "', '"
                + dob + "');";
    }

    /**
     * getting the Id for the new record to insert
     *
     * we are simply getting all values and getting the new id
     * checking that is not already existing as it has to be unique.
     * we are following an incremental approach to this case
     */
    private int getnewID() {

        //default value 1000 for this example
        //in case of a bigger application we would need to use another approach as this has limitations
        int[] user_id = new int[1000];

        List<Integer> id = new ArrayList<>();

        try {
            pstmt = con.prepareStatement("SELECT * FROM g_user");
            result = pstmt.executeQuery();

            int i = 0;
            //adding all ids to a list
            while (result.next()) {
                user_id[i] = result.getInt("user_id");
                id.add(user_id[i]);
                i++;
            }
            pstmt.close();
        } catch (Exception e) {
            System.err.println("System Exception while getting the next ID: " + e + "\nPlease Try Again");
            //giving time to the program to write all messages otherwise the exception will be displayed on the wrong position
            try {
                new Thread().sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Error while waiting");
            }
        }
        return id.size() + 1;
    }

    /**
     *  checking if connection was closed
     */
    private boolean isClosed() throws SQLException {
        return con.isClosed();
    }
}
