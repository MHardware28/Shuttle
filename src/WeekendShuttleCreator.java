public class WeekendShuttleCreator extends ShuttleCreator {
    @Override
    public ShuttleService createShuttle() {
        ShuttleService shuttle = ShuttleService.getInstance();
        shuttle.setRoute(null);   //No fixed route
        return shuttle;
    }
}

