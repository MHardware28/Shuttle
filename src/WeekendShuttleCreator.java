import java.util.ArrayList;
import java.util.List;

public class WeekendShuttleCreator extends ShuttleCreator {

    @Override
    public ShuttleService createShuttle() {
        ShuttleService shuttle = ShuttleService.getInstance();
        shuttle.resetRoute();

        //weekend stop list
        Route route = new Route(List.of(
                new Stop("Home2Suites Hotel"),
                new Stop("USF Atala Hall"),
                new Stop("NCF Sudakoff"),
                new Stop("Marine Bio Building")
        ));

        shuttle.setRoute(route);
        return shuttle;
    }
}

