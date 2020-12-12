import java.sql.SQLException;

public interface DBConnection {

    boolean connect(String args[]) throws SQLException;

    void close_connection();

    void listUser(int iMaxNumRows);

    void insert(String name, String surname, String dob);

}
