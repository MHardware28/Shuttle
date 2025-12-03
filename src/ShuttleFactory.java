import java.util.ArrayList;
import java.util.List;
/*  Creates a ShuttleService based on the specified shuttle type.
Factory Method is used to select and create the appropriate concrete shuttle creator (Day, Night, or Weekend) at runtime.
*/  
public class ShuttleFactory {

    public static ShuttleService createShuttle(ShuttleType type) {

        ShuttleCreator creator;
// Choose the shuttle to make depending on the type 
        
        switch (type) {
            case DAY:
                creator = new DayShuttleCreator();
                break;
            case NIGHT:
                creator = new NightShuttleCreator();
                break;
            case WEEKEND:
                creator = new WeekendShuttleCreator();
                break;
            default:
                // If the type does not match any known case, throw an error
                throw new IllegalArgumentException("Unknown shuttle type " + type);
        }

        return creator.createShuttle();
    }
}


