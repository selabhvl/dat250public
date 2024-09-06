package no.hvl.dat250.l06;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
public class LocationController {

    private final LocationRepository repo;

    public LocationController(@Autowired LocationRepository repo) {
        this.repo = repo;
    }



    @GetMapping("/locations")
    public Collection<Location> getAllLocationsHandler() {
        return repo.getAllLocations();
    }

    @GetMapping("/locations/{name}")
    public ResponseEntity<Location> getLocationHandler(@PathVariable String name) {
        return repo.getLocation(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/locations")
    public ResponseEntity<Location> createLocationHandler(@RequestBody Location location) throws LocationRepository.AlreadyExistsException {
        this.repo.addLocation(location);
        return ResponseEntity.created(URI.create("/locations/" + location.getName())).body(location);
    }


    @PutMapping("/locations/{name}")
    public ResponseEntity<Location> updateLocationHandler(@PathVariable String name, @RequestBody Location location) {
        Optional<Location> result = this.repo.getLocation(name);
        if (result.isPresent()) {
            Location current = result.get();
            current.setPrecipitation(location.getPrecipitation());
            current.setTemperature(location.getTemperature());
            return ResponseEntity.ok(current);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/locations/{name}")
    public ResponseEntity<?> deleteLocationHandler(@PathVariable String name) {
        boolean wasPresent = this.repo.deleteLocation(name);
        if (wasPresent) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
