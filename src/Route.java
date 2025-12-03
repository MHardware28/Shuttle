import java.util.List;
/*
  Represents a shuttle route, which is made up of a list of stops.
 */
public class Route {
    protected List<Stop> stops;
// Creates a route using the list of stops provided 
    public Route(List<Stop> stops){
        this.stops = stops;
    }
//  Returns the list of stops in this route.
    public List<Stop> getStops(){
        return stops;
    }
}
