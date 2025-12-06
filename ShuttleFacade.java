
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * AUTHORED BY RICH-ANN
 * The ShuttleFacade class hides the complicated logic of the shuttle system.
 * The shuttle logic involves multiple classes:
 *      • ShuttleSimulation
 *      • ShuttleContext (State Pattern)
 *      • ShuttleObserver & Observers
 *      • Stop objects
 * - Instead of the GUI talking to all those classes, the GUI ONLY talks
 *   to the Facade. This keeps the GUI clean and the logic separated.
 *
 * The facade:
 * 1. Loads all shuttle stops.
 * 2. Starts the shuttle simulation (Day, Night, Weekend).
 * 3. Manages subscriptions to shuttle stop updates.
 * 4. Forwards observer notifications to the GUI.
 * 5. Provides helper methods such as:
 *      - selectShuttleType()
 *      - subscribeStop()
 *      - unsubscribeStop()
 *      - stopSimulation()
 *      - getStops() for building buttons
 */

public class ShuttleFacade implements ShuttleFacadeInterface {
//new instance of observer object
    private final ShuttleObserver shuttleObserver = new ShuttleObserver();
//factory
    private ShuttleService shuttleService;
    private final ShuttleContext shuttleContext;//state

    private Timer timer;
    private TimerTask task;

    private List<Stop> stops;//list of all stops
    private int currentIndex = -1;//tracks the next stop(starts at 0)
    private String currentDestination = null;

    //ETA  in seconds
    private int currentEtaSeconds = 0;

    private final Random rand = new Random();//random eta generator

    private Observer currentStopObserver = null;//obs for one stop


    private boolean running = false;//one timer at a time

    private ShuttleType selectedType;



    public ShuttleFacade() {
        //the context requires the observer system so state transitions can notify subscribers
        shuttleContext = new ShuttleContext(shuttleObserver);
        shuttleService = null;//no shuttle selected by default
    }

    public void selectShuttleType(ShuttleType type) {
        //select shuttle type from factory
        this.selectedType = type;
        shuttleService = ShuttleFactory.createShuttle(type);

        if (shuttleService.getRoute() != null) {// Extract route’s stop list
            stops = shuttleService.getRoute().getStops();
        } else {
            stops = null;
        }

        currentIndex = -1;
        currentDestination = null;
        currentEtaSeconds = 0;
        stopTimerIfRunning();
    }

    public ShuttleType getSelectedType(){
        return selectedType;
    }

   public void startSimulation() {
        //TimerTask executes repeatedly every 2 seconds to simulate movement.
        if (running) return;//dint start twice
        if (stops == null || stops.isEmpty()) return; //weekend = no route THEREFORE no simulation

        running = true;//running
        prepareNextStop();

        timer = new Timer(true);
        task = new TimerTask() {
            @Override
            public void run() {
                tick();

            }

        };
        tick();

//then tick every 2 seconds
        timer.scheduleAtFixedRate(task, 0, 2000);
    }

        public void stopSimulation() {
        running = false;//chsnge running to false to stop simulation loop
        stopTimerIfRunning();//shut down timer
    }

    private void stopTimerIfRunning() {
        if (task != null) task.cancel();//cancel if timerTask ended
        if (timer != null) {
            timer.cancel();

        }
        task = null;
        timer = null;
    }

    public void requestWeekendPickup() {
        if (shuttleService == null || shuttleService.getRoute() != null)
            return;

        shuttleObserver.updateShuttleStatus("Weekend Shuttle Requested", 0.0);
    }




    public void subscribeStop(String stopName, Observer guiObserver) {
        unsubscribeCurrent();//remove other subscriptions


        //obs that filters updates about ONE stop
        Observer filter = (location, time) -> {
            if (location != null && location.contains(stopName)) {
                guiObserver.update(location, time);
            }
        };

        currentStopObserver = filter;
        shuttleObserver.addObserver(filter);
    }

    public void unsubscribeStop() {
        if (currentStopObserver != null) {
            shuttleObserver.removeObserver(currentStopObserver);//stop updates
        }
        currentStopObserver = null;


    }

    public void unsubscribeCurrent() {

        unsubscribeStop();
    }


    private void tick() {
        if (!running || stops == null || currentDestination == null) return;

        //Countdown drops exactly 1 minute per tick
        currentEtaSeconds = Math.max(0, currentEtaSeconds - 60);

        if (currentEtaSeconds == 0) {
            //ARRIVED
            shuttleContext.setState(new ArrivingState());
            shuttleContext.updateStatus("Shuttle has arrived at " + currentDestination, 0.0);

            prepareNextStop();
            return;
        }

        //ENROUTE
        shuttleContext.setState(new EnrouteState());
        double etaMin = currentEtaSeconds / 60.0; //secs to min
        shuttleContext.updateStatus("Shuttle is enroute to " + currentDestination, etaMin);
    }

    private void prepareNextStop() {

        if (stops.size() == 1) {
            shuttleContext.setState(new ArrivingState());
            shuttleContext.updateStatus("Shuttle has arrived at " + stops.get(0).getName(), 0.0);
            stopSimulation();
            return;
        }

        //move to next stop
        currentIndex = (currentIndex + 1) % stops.size();
        currentDestination = stops.get(currentIndex).getName();



        String previousStop = stops.get((currentIndex - 1 + stops.size()) % stops.size()).getName();
        shuttleContext.setState(new LeavingState()); //pul on leaving state logic
        shuttleContext.getState().isLeaving(shuttleContext, previousStop);



        //Random ETA between 2-10
        int randomMinutes = rand.nextInt(9) + 2;
        currentEtaSeconds = randomMinutes * 60;

        //ENROUTE state
        shuttleContext.setState(new EnrouteState());
        shuttleContext.updateStatus(
                "New Shuttle is enroute to " + currentDestination,
                randomMinutes
        );
    }


    public List<Stop> getStops() {//load stop butons
        return stops;
    }
    public void unsubscribeAll() {
        unsubscribeCurrent();
    }
}
