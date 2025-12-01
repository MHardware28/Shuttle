public class ShuttleService {
    private static ShuttleService instance;
    protected Route route;


    private ShuttleService(){
    }
    public static ShuttleService getInstance(){
        if(instance == null) {
            instance = new ShuttleService();
        }
        return instance;
    }

    public void setRoute(Route route){
        this.route = route;
    }

    public Route getRoute(){

        return route;
    }
    public void resetRoute() {
        this.route = null;
    }

        return route;
    }

}
