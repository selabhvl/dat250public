package no.hvl.dat250.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}


	@Bean
	public CommandLineRunner startupActions(UserRepository userRepository) {
		return args -> {
			User admin = new User("admin", "admin@company.com", "1234");
			admin.setRole(User.Roles.PRIVILEGED);

			User bob = new User("bob", "bob@bobsoft.org", "bob");

			userRepository.save(admin);
			userRepository.save(bob);
		};
	}

}
