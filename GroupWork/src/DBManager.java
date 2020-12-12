import java.sql.*;

public class DBManager implements DBConnection {

    private final String URL = "jdbc:postgresql://devweb2020.cis.strath.ac.uk:5432/cs253";

    Connection con;
    ResultSet result;
    PreparedStatement pstmt;
    static String name;
    static String sUserPwd;

    @Override
    public boolean connect(String args[]) throws SQLException {
        if (getConnection(args)) {
            return true;
        }
        return false;
    }

    @Override
    public void close_connection() {
        closeConnection();
    }

    private boolean getConnection(String args[]) throws SQLException {
        //checking if arguments are present in the run config
        if (args.length == 2) {
            name = args[0];
            sUserPwd = args[1];

            // Create a new JDBC connection. This is our session.
            con = DriverManager.getConnection(URL, name, sUserPwd);
            //TODO: might need to be set to true once the app is complete
            con.setAutoCommit(false);
            return true;
        } else {
            System.out.println("Wrong quantity of arguments");
            new Message().usage();
            return false;
        }
    }

    public void closeConnection() {
        try {
            con.close();
            System.out.println("Connection close successfully");
        } catch (Exception e) {
            System.err.println("System Exception in closeConnection: " + e);
        }
    }

    public String listUser(int iMaxNumRows) {
        StringBuilder retVal = new StringBuilder(new String("NAME\tSURNAME\tDOB\n"));

        int[] NAME = new int[iMaxNumRows];
        String[] SURNAME = new String[iMaxNumRows];
        String[] DOB = new String[iMaxNumRows];

        try {
            pstmt = con.prepareStatement(
                    "SELECT name, surname, dob FROM g_user ORDER BY name");
            result = pstmt.executeQuery();

            int i = 0;
            while (result.next()) {
                NAME[i] = result.getInt("name");
                SURNAME[i] = result.getString("surname");
                DOB[i] = result.getString("dob");
                retVal.append(NAME[i]).append("\t").append(SURNAME[i]).append("\t").append(DOB[i]).append("\n");
                i++;
            }
            pstmt.close();
        }
        catch (Exception e) {
            System.err.println("System Exception in User table " + e);
        }
        return(retVal.toString());
    }
}
