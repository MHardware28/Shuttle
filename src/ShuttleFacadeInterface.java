public interface ShuttleFacadeInterface {

    //selects which shuttle the user wants
    void selectShuttleType(ShuttleType type);

    //start simulation
    void startSimulation();

    //stops the shuttle simulation eta
    void stopSimulation();



    //subscribe to our GUI to receive updates for a stop
    void subscribeStop(String stopName, Observer guiObserver);

    //unsubscribe from current observer
    void unsubscribeCurrent();


    void unsubscribeStop();


    //get list of stops for populating GUI buttons
    java.util.List<Stop> getStops();


    //remove all observers
    void unsubscribeAll();
}

