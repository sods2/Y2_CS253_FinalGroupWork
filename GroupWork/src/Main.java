import java.sql.SQLException;

public class Main {

    protected static DBConnection dbConnection = new DBManager();

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        int times = 0;

        while (true) {
            //getting the connection to the DB only on the first iteration
            if (times == 0) {
                int try_con = 0;

                try {
                    //try connecting 3 times before returning error
                    while (!dbConnection.connect(args)) {
                        try_con++;
                        if (try_con > 2) {
                            break;
                        }
                    }


                } catch (SQLException e) {
                    System.out.println("An error occur while trying to connect to the database");
                    new Message().usage();
                    System.err.println("System Exception in connect: " + e);
                }
                //giving time to the program to write all messages otherwise the exception will be displayed after the insert Input message
                new Thread().sleep(100);
                new Message().welcome();
                times++;
            }
            if (times == 1) {
                app.run();
            }
        }
    }
}
