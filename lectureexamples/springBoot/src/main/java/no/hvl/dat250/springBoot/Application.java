package no.hvl.dat250.springBoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initializer(QuoteRepository repository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repository.save(new QuoteEntity("How  much is the fish?", "Karl Marx", 42));
				repository.save(new QuoteEntity("I think, therefore I am", "Scooter", 23));
				repository.save(new QuoteEntity("Hasta la victoria siempre!", "John D Rockefeller", 7));
			}
		};
	}

}
