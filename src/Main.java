public class Main {
    public static void main(String[] args) {
        ShuttleObserver observer1 = new ShuttleObserver();
        ShuttleContext context = new ShuttleContext(observer1);

        // Example observer
        observer1.addObserver((location, time) -> {
            System.out.println("UI Updated: " + location + " | ETA: " + time);
        });
        
        ShuttleService shuttleService = ShuttleFactory.createShuttle();
        System.out.println("Shuttle Route Stops");
        for(Stop s: shuttleService.getRoute().getStops()){
            System.out.println("  " + s.getName());
    

        // creates the different states then notifies the observers
        context.setState(new LeavingState());
        context.leaving("Home2Suites Hotel");  // Notifies observers
        context.enroute("USF Atala Hall");  // Switches to EnrouteState + notifies observers
        context.arriving("NCF Sudakoff"); // Switches to ArrivingState + notifies observers
    }
}
