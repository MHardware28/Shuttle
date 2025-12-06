// This is the interface that my shuttle observer uses to update each subscriber
public interface Observer {
    void update(String shuttleLocation, double shuttleTime);
}
