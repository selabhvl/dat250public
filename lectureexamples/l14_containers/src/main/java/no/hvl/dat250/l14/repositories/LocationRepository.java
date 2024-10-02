package no.hvl.dat250.l14.repositories;

import no.hvl.dat250.l14.entities.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    public Collection<Location> getLocationsByName(String name);
}
