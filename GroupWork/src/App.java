public class App {

    Console console = new Console();
    Message message = new Message();
    TrafficLight trafficLight;

    public void run() {
        message.start();
        trafficLight = new TrafficLight(console, console.getInput());
    }
}
