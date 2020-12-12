import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private Scanner scanner;

    public String getInput() {
        System.out.println("Insert input:\n");
        scanner = new Scanner(System.in);
        return scanner.next();
    }

    public int getInput_Integer() {
        System.out.println("Insert input:\n");
        scanner = new Scanner(System.in);

        int nRows = 0;

        try {
            scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please insert a valid quantity. Must be a digit\n");
            getInput_Integer();
        }
        return nRows;
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
        new DBManager().listUser(getInput_Integer());
    }

//TODO:
//
//    public void insert(){
//        try{
//            System.out.println("");
//        } catch (){
//            System.out.println(e);
//        }
//    }

    public void exit() {
        Main.dbConnection.close_connection();
        new Message().goodbye();
        System.exit(0);
    }

}
