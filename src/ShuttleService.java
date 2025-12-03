// Author: Sara-Lee Brown 
/*
ShuttleService implements a Singleton pattern that manages the current shuttle Route.
Only one wants one instance of a shuttle.
*/
public class ShuttleService {
    private static ShuttleService instance;
    protected Route route;


    private ShuttleService(){
    }
    
   /* Returns the single instance of ShuttleService.
      If it does not exist yet, it creates one.
    */
     
    public static ShuttleService getInstance(){
        if(instance == null) {
            instance = new ShuttleService();
        }
        return instance;
    }
    
    /* 
    Set the current route 
    Return the current route 
    Clears the current route, resetting the service to its initial state.
    */

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
