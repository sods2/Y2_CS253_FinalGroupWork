public class TrafficLight {

    TrafficLight(Console console, String command){
        switch (command){
            case "insert":
//                console.insert();
                break;
            case "query":
//                console.query();
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
