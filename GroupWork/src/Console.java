import java.util.Scanner;

public class Console {

    private Scanner scanner;

    public String getInput(){
        System.out.println("Insert input:\n");
        scanner = new Scanner(System.in);
        return scanner.next();
    }

    public String getInput_Date(){
        System.out.println("Insert date:\n");
        scanner = new Scanner(System.in);
        String date;
        while (!(date = scanner.next()).matches("\\d{4}-\\d{2}-\\d{2}")){
            System.out.println("Please insert a valid date format. Ex. (yyyy-MM-dd)");
        }
        return date;
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
//
//    public void query(){
//        try{
//            System.out.println("");
//        } catch (){
//            System.out.println(e);
//        }
//    }

    public void exit(){
        Main.dbConnection.close_connection();
        new Message().goodbye();
        System.exit(0);
    }

}
