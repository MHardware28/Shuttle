import java.util.ArrayList;
import java.util.List;

public class ShuttleFactory {
    public static List< Stop> createStops(){
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("Home2Suites Hotel"));
        stops.add(new Stop("USF Atala Hall"));
        stops.add(new Stop("NCF Sudakoff"));
        stops.add(new Stop("Pritzker Marine Biology"));
        return stops;
    }
    // Build route
    public static Route createRoute(){
        return new Route(createStops());
    }
    // Return the Singleton Shuttle
    public static ShuttleService createShuttle(){
        ShuttleService shuttleService = ShuttleService.getInstance();
        shuttleService.setRoute(createRoute());
        return shuttleService;

    }

}
