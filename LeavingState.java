public class LeavingState implements State{

    @Override
    public void isLeaving(ShuttleContext context, String shuttleLocation){
        context.updateStatus("- Pick up round completed - Shuttle Leaving: "+ shuttleLocation, 0.0);
    }
    @Override
    public void enroute(ShuttleContext context, String shuttleLocation){
        context.setState(new EnrouteState());
        context.updateStatus("Shuttle is enroute to: " + shuttleLocation, 5.0);
    }
    @Override
    public void isArriving(ShuttleContext context, String shuttleLocation){

        System.out.println("Cannot arrive if shuttle is leaving.");
    }
}