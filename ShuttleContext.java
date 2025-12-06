

public class ShuttleContext {
    private State currentState;
    private ShuttleObserver observerSystem;

    public ShuttleContext(ShuttleObserver observerSystem) {
        this.observerSystem = observerSystem;
        this.currentState = new LeavingState();
    }

    public void setState(State state) {
        this.currentState = state;
    }
    public State getState() {
        return this.currentState;
    }

    public void leaving(String shuttleLocation) {
        currentState.isLeaving(this, shuttleLocation);
    }

    public void enroute(String shuttleLocation) {
        currentState.enroute(this, shuttleLocation);
    }

    public void arriving(String shuttleLocation) {
        currentState.isArriving(this, shuttleLocation);
    }

    public void updateStatus(String location, double time) {
        observerSystem.updateShuttleStatus(location, time);
    }
}
