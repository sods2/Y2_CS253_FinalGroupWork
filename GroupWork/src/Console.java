import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private Scanner scanner;

    private int nRows = 0;

    /**
     * Getting any type of input form the user
     * @return string of the input
     */
    public String getInput() {
        System.out.println("Insert input:\n");
        scanner = new Scanner(System.in);
        return scanner.next();
    }

    /**
     * Getting integer type of input form the user and checks if is the right format
     * @return true if the input was an integer
     */
    public boolean getInput_Integer() {
        System.out.println("Insert input:\n");
        scanner = new Scanner(System.in);

        try {
            nRows = scanner.nextInt();
            return true;
        } catch (InputMismatchException e) {
            System.out.println("Please insert a valid quantity. Must be a digit and more then 0\n");
            return false;
        }
    }

    /**
     * get aa date input from the user
     * performs a check to verify that the date is matching the right pattern
     * @return date
     */
    public String getInput_Date() {
        System.out.println("Insert date:\n");
        scanner = new Scanner(System.in);
        String date;
        while (!(date = scanner.next()).matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Please insert a valid date format. Ex. (yyyy-MM-dd)\n");
        }
        return date;
    }

    /**
     * getting the input and calling the method listUser to display the result of a select query
     */
    public void getUserData() {
        while (!getInput_Integer());
        Main.dbConnection.listUser(nRows);
    }

    /**
     * we ask the user all the data that we need for the insert command and then we call the insert method to commit the data into the database
     */
    public void insert(){
        System.out.println("Insert value for name:\n");
        String name = getInput();
        System.out.println("Insert value for surname:\n");
        String surname = getInput();
        System.out.println("Insert value for dob:\n");
        String dob = getInput_Date();
        Main.dbConnection.insert(name, surname, dob);
    }

    /**
     * we make sure that the connection to the database is closed and then we exit the application
     */
    public void exit() {
        Main.dbConnection.close_connection();
        new Message().goodbye();
        System.exit(0);
    }

}
