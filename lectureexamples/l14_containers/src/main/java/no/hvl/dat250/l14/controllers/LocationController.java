package no.hvl.dat250.l14.controllers;

import no.hvl.dat250.l14.entities.Location;
import no.hvl.dat250.l14.entities.Observation;
import no.hvl.dat250.l14.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationRepository repository;

    public LocationController(@Autowired LocationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Location> getLocations() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/{location}")
    public ResponseEntity<Location> getLocation(@PathVariable String location) {
        return repository.getLocationsByName(location).stream().findFirst().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{location}")
    public ResponseEntity<Location> createLocation(@PathVariable String location, @RequestBody Location body) {
        body.setName(location);
        Location saved = repository.save(body);
        return ResponseEntity.created(URI.create("/" + location)).body(saved);
    }

    @DeleteMapping("/{location}")
    public ResponseEntity<Void> deleteLocation(@PathVariable String location) {
        Optional<Location> foundLocations = repository.getLocationsByName(location).stream().findFirst();
        if (foundLocations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Location toDelete = foundLocations.get();
        repository.delete(toDelete);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{location}/observations")
    public ResponseEntity<List<Observation>> getObservationsAt(@PathVariable String location) {
        Optional<Location> foundLocations = repository.getLocationsByName(location).stream().findFirst();
        Optional<List<Observation>> obs = foundLocations.map(l -> l.getObservations().stream().sorted((Comparator.comparing(Observation::getTimestamp))).collect(Collectors.toList()));
        return obs.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/{location}/observations")
    public ResponseEntity<List<Observation>> getObservationsAt(@PathVariable String location, @RequestBody Observation obs) {
        Optional<Location> foundLocations = repository.getLocationsByName(location).stream().findFirst();
        if (foundLocations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Location loc = foundLocations.get();
        loc.getObservations().add(obs);
        repository.save(loc);

        return ResponseEntity.ok(loc.getObservations());
    }


}
