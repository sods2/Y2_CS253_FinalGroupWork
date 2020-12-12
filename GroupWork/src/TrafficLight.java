public class TrafficLight {

    /**
     * We are using this class to manage all the available commands on the application
     *
     * @param console Console obj we will need to get the console methods
     * @param command the command that the user inputted into the app
     */
    TrafficLight(Console console, String command){
        switch (command){
            case "insert":
                console.insert();
                break;
            case "query":
                console.getUserData();
                break;
            case "usage_help":
                new Message().usage_help();
                break;
            case "exit":
                console.exit();
                break;
            default:
                new Message().cmdUknown();
                break;
        }
    }

}
