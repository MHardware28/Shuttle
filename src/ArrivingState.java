//This class deals with the arriving state of the shuttle, if the shuttle is already arriving it updates the status to output that message.
public class ArrivingState implements State{

    @Override
    public void isLeaving(ShuttleContext context, String shuttleLocation){
        System.out.println("Shuttle has already left.");
    }
    @Override
    public void enroute(ShuttleContext context, String shuttleLocation){
        System.out.println("Shuttle is already arriving.");
    }
    @Override
    public void isArriving(ShuttleContext context, String shuttleLocation){
        context.updateStatus("Arrived", 0.0);
    }
}
