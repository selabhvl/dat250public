package no.hvl.dat250.l14;

import no.hvl.dat250.l14.entities.Location;
import no.hvl.dat250.l14.entities.Observation;
import no.hvl.dat250.l14.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SpringBootApplication
public class L14ContainersApplication {

	public static void main(String[] args) {
		SpringApplication.run(L14ContainersApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(@Autowired LocationRepository repository) {
		return args -> {
			Location bergen = new Location("bergen", 60.36, 5.35);

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

			repository.save(bergen);
		};
	}

}
