/**
 * Purpose: Defines the interface for shuttle states in the State Pattern.
 * Each concrete state (Leaving, Enroute, Arriving) implements these behaviors.
 */
public interface State {

    /**
     * Handles the behavior when the shuttle is leaving at a location
     */
    void isLeaving(ShuttleContext context, String shuttleLocation);
    /**
     * Handles the behavior when the shuttle is enroute at a location
     */
    void enroute(ShuttleContext context, String shuttleLocation);
    /**
     * Handles the behavior when the shuttle is arriving at a location
     */
    void isArriving(ShuttleContext context, String shuttleLocation);
}
