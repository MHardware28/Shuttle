import java.util.ArrayList;
import java.util.List;

public class ShuttleFactory {

    public static ShuttleService createShuttle(ShuttleType type) {

        ShuttleCreator creator;

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
                throw new IllegalArgumentException("Unknown shuttle type " + type);
        }

        return creator.createShuttle();
    }
}


