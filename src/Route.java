import java.util.List;

public class Route {
    protected List<Stop> stops;

    public Route(List<Stop> stops){
        this.stops = stops;
    }

    public List<Stop> getStops(){
        return stops;
    }
}
