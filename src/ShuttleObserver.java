/**
 * Purpose: Acts as the Subject in the Observer Pattern, managing a list of
 * observers and notifying them whenever the shuttle's status changes.
 */
import java.util.ArrayList;
import java.util.List;

// List of all observers subscribed to shuttle status updates
public class ShuttleObserver {
    private List<Observer> observers = new ArrayList<>();
    // Stores the most recent shuttle time and location
    private double shuttleTime;
    private String shuttleLocation;

    /**
     * Adds and removes an observer to the notification list 
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Updates the current shuttle status (location + time)
     * and notifies all subscribed observers
     */
    public void updateShuttleStatus(String shuttleLocation, double shuttleTime) {
        this.shuttleLocation = shuttleLocation;
        this.shuttleTime = shuttleTime;
        notifyObservers();
    }

    /**
     * Notifies each observer about the latest shuttle status
     */
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(shuttleLocation, shuttleTime);
        }
    }
}

