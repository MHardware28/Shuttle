import java.util.ArrayList;
import java.util.List;

public class ShuttleObserver {
    private List<Observer> observers = new ArrayList<>();
    private double shuttleTime;
    private String shuttleLocation;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void updateShuttleStatus(String shuttleLocation, double shuttleTime) {
        this.shuttleLocation = shuttleLocation;
        this.shuttleTime = shuttleTime;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(shuttleLocation, shuttleTime);
        }
    }
}

