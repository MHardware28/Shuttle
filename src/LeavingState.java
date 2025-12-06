//This class deals with the leaving state of the shuttle, if the shuttle has already left it updates the status to output that message as well as giving an estimate as to when it will get to the other location.
public class LeavingState implements State{

    @Override
    public void isLeaving(ShuttleContext context, String shuttleLocation){
        context.updateStatus("Leaving: "+ shuttleLocation, 0.0);
    }
    @Override
    public void enroute(ShuttleContext context, String shuttleLocation){
        context.setState(new EnrouteState());
        context.updateStatus("- Pick up round completed - Shuttle Leaving: " + shuttleLocation, 5.0);
    }
    @Override
    public void isArriving(ShuttleContext context, String shuttleLocation){

        System.out.println("Cannot arrive if shuttle is leaving.");
    }
}
