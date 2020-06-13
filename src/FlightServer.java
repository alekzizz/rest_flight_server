import filter.LoginFilter;
import resource.FlightResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class FlightServer extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(FlightResource.class);
        h.add(LoginFilter.class);
        return h;
    }
}
