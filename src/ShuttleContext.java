/**
 * Purpose: Manages the current state of the shuttle in the State Pattern
 * and coordinates status updates with the observer system/pattern.
 */
public class ShuttleContext {
    // Tracks the current state of the shuttle (e.g., Arriving, Enroute, Leaving)
    private State currentState;
    // Observer system used to notify listeners of shuttle status changes
    private ShuttleObserver observerSystem;

    /**
     * Constructor initializes the context with an observer system
     */
    public ShuttleContext(ShuttleObserver observerSystem) {
        this.observerSystem = observerSystem;
        this.currentState = new LeavingState();
    }

     /**
     * Updates the current state of the shuttle
     */
    public void setState(State state) {
        this.currentState = state;
    }
    public State getState() {
        return this.currentState;
    }

    /**
     * Handles the "leaving", "Enorute" and "arriving" behavior for the current state
     */
    public void leaving(String shuttleLocation) {
        currentState.isLeaving(this, shuttleLocation);
    }

    public void enroute(String shuttleLocation) {
        currentState.enroute(this, shuttleLocation);
    }

    public void arriving(String shuttleLocation) {
        currentState.isArriving(this, shuttleLocation);
    }

    /**
     * Sends updated shuttle status (location + time) to the observer system
     */
    public void updateStatus(String location, double time) {
        observerSystem.updateShuttleStatus(location, time);
    }
}
