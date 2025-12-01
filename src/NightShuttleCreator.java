import java.util.List;

public class NightShuttleCreator extends ShuttleCreator {
    @Override
    public ShuttleService createShuttle() {
        ShuttleService shuttle = ShuttleService.getInstance();
        shuttle.resetRoute();
        Route route = new Route(List.of(
                new Stop("Home2Suites Hotel"),
                new Stop("USF Atala Hall"),
                new Stop("NCF Sudakoff")
        ));

        shuttle.setRoute(route);
        return shuttle;
    }
}

