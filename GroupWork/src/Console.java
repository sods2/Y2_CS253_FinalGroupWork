import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private Scanner scanner;

    private int nRows = 0;

    public String getInput() {
        System.out.println("Insert input:\n");
        scanner = new Scanner(System.in);
        return scanner.next();
    }

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

    public String getInput_Date() {
        System.out.println("Insert date:\n");
        scanner = new Scanner(System.in);
        String date;
        while (!(date = scanner.next()).matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Please insert a valid date format. Ex. (yyyy-MM-dd)\n");
        }
        return date;
    }

    public void getUserData() {
        while (!getInput_Integer());
        Main.dbConnection.listUser(nRows);
    }

    public void insert(){
        System.out.println("Insert value for name:\n");
        String name = getInput();
        System.out.println("Insert value for surname:\n");
        String surname = getInput();
        System.out.println("Insert value for dob:\n");
        String dob = getInput_Date();
        Main.dbConnection.insert(name, surname, dob);
    }

    public void exit() {
        Main.dbConnection.close_connection();
        new Message().goodbye();
        System.exit(0);
    }

}
