//This class deals with the enroute state of the shuttle, if the shuttle is already enroute to its destination it updates the status to output that message as well as give a estimated time of arrival..
public class EnrouteState implements State {

    @Override
    public void isLeaving(ShuttleContext context, String shuttleLocation){
        System.out.println("Already enroute. Shuttle has already left this stop.");
    }
    @Override
    public void enroute(ShuttleContext context, String shuttleLocation){
        context.updateStatus("Shuttle is enroute." ,3.0);
    }
    @Override
    public void isArriving(ShuttleContext context, String shuttleLocation){
        context.setState(new ArrivingState());
        context.updateStatus("Arriving Soon: " + shuttleLocation, 1.0);
    }
}
