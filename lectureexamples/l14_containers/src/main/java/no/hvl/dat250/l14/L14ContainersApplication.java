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
}
