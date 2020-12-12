import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Message {

    public void welcome(){
        System.out.println("\nWelcome to the application\n");
    }

    public void start(){
        System.out.println( "\n" +
                "+--------------------------+\n" +
                "| Select 1 of the options  |\n" +
                "|                          |\n" +
                "|  1.  insert              |\n" +
                "|  2.  query               |\n" +
                "|  4.  exit                |\n" +
                "|                          |\n" +
                "+--------------------------+\n" +
                "\n");
    }

    public void usage(){
        System.out.println("java jdbc_ex <username> <password>\n" +
                "Username and Password needs to be passed as arguments in the run config\n" +
                "use command 'usage_help' to get more info");
    }

    public void usage_help(){
        System.out.println("You need to pass your PostgreSQL user-id and password as input arguments to your program. To do so:\n" +
                            "\n" +
                            "\t1. Go to Run menu and select Edit Configuration.\n" +

                            "\t2. On the left-hand side of the dialog box you should see Application.\n" +
                            "\t   If you don't see it, in the upper left corner of the dialog box, click the + icon and in the Add New Configuration list select Application.\n" +
                            "\t   Once you have Application, click on the pull-down triangle next to it to open the list of applications.\n" +

                            "\t3. Click on your java program in the list. This will open the configuration page for your program.\n" +

                            "\t4. In the Program arguments field, write your input arguments separated by a space. " +
                            "For this coursework, it should be your username and password.");
    }

    public void cmdUknown(){
        System.out.println("Please enter one of the valid commands presented on the list\n" +
                "ex. (insert, query or exit)");
    }

    public void goodbye(){
        System.out.println("Thanks for using our application.\n" +
                "Goodbye");
    }

}
