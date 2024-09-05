package no.hvl.dat250.l06;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class LocationRepository {

    private final Map<String, Location> locationMap = new HashMap<>();

    public void addLocation(Location l) throws AlreadyExistsException {
        if (locationMap.containsKey(l.getName())) {
            throw new AlreadyExistsException(l.getName());
        }
        locationMap.put(l.getName(), l);
    }

    public Optional<Location> getLocation(String name) {
        if (locationMap.containsKey(name)) {
            return Optional.of(locationMap.get(name));
        }
        return Optional.empty();
    }

    public boolean deleteLocation(String name) {
        return this.locationMap.remove(name) != null;
    }

    public Collection<Location> getAllLocations() {
        return locationMap.values();
    }


    public static class AlreadyExistsException extends Exception {
        private final String location;

        public AlreadyExistsException(String location) {
            super("Location '" + location + "' already exists!");
            this.location = location;
        }

        public String getLocation() {
            return location;
        }
    }

}
