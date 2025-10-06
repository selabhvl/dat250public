package no.hvl.dat250.l14;


import no.hvl.dat250.l14.entities.Location;
import no.hvl.dat250.l14.entities.Observation;
import no.hvl.dat250.l14.repositories.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DatabaseTest {

    @Autowired
    private LocationRepository repository;

    @Test
    public void testSavingAndLoadingLocations() {
        Location bergen = new Location("bergen", 60.36, 5.35);
        Location oslo = new Location("oslo", 59.91, 10.63);

        assertNull(bergen.getId());
        assertNull(oslo.getId());
        bergen = repository.save(bergen);
        oslo = repository.save(oslo);

        bergen.getObservations().add(new Observation(
                bergen,
                LocalDateTime.of(2024, 10, 2, 12, 0).toInstant(ZoneOffset.of("+2")),
                14,
                "degC",
                Observation.ObservationType.TEMPERATURE));
        bergen.getObservations().add(new Observation(
                bergen,
                LocalDateTime.of(2024, 10, 2, 12, 0).toInstant(ZoneOffset.of("+2")),
                0,
                "mm",
                Observation.ObservationType.PRECIPITATION));
        bergen.getObservations().add(new Observation(
                bergen,
                LocalDateTime.of(2024, 10, 2, 12, 0).toInstant(ZoneOffset.of("+2")),
                4,
                "m/s",
                Observation.ObservationType.WIND));


        bergen.getObservations().add(new Observation(
                bergen,
                LocalDateTime.of(2024, 10, 2, 12, 0).toInstant(ZoneOffset.of("+2")),
                13,
                "degC",
                Observation.ObservationType.TEMPERATURE));

        bergen.getObservations().add(new Observation(
                bergen,
                LocalDateTime.of(2024, 10, 2, 12, 0).toInstant(ZoneOffset.of("+2")),
                2,
                "m/s",
                Observation.ObservationType.WIND));


        assertNull(bergen.getObservations().get(1).getId());
        repository.save(bergen);


        Optional<Location> osloStored = repository.getLocationsByName("oslo").stream().findFirst();
        assertTrue(osloStored.isPresent());
        assertNotNull(osloStored.get().getId());
        assertEquals(0, osloStored.get().getObservations().size());
        assertEquals(59.91, osloStored.get().getLatitude());

        assertEquals(0, repository.getLocationsByName("stavanger").size());

        Optional<Location> bergenStored = repository.getLocationsByName("bergen").stream().findFirst();
        assertTrue(bergenStored.isPresent());
        assertEquals(5, bergenStored.get().getObservations().size());
        assertTrue(bergenStored.get().getObservations().stream().allMatch(o -> o.getId() != null));



    }

}
