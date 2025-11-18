public interface State {

    void isLeaving(ShuttleContext context, String shuttleLocation);
    void enroute(ShuttleContext context, String shuttleLocation);
    void isArriving(ShuttleContext context, String shuttleLocation);
}
