package no.hvl.dat250.nosql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import no.hvl.dat250.nosql.entities.Poll;
import no.hvl.dat250.nosql.entities.User;
import no.hvl.dat250.nosql.entities.VoteOption;
import no.hvl.dat250.nosql.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication(exclude = {
		// mongo
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class,
		// redis
		RedisAutoConfiguration.class,
		RedisRepositoriesAutoConfiguration.class,
		// JPA
//		DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class,
//		HibernateJpaAutoConfiguration.class
})
public class NosqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NosqlApplication.class, args);
	}

	public static Collection<User> populateTestDate() {
		User alice = new User("alice", "alice@online.com");
		User bob = new User("bob", "bob@bob.net");
		User eve = new User("eve", "eve@mail.org");
		Poll p1 = alice.createPoll("Pineapple on Pizza");
		VoteOption o1 = p1.addOption("Mamma mia: Nooooo!");
		VoteOption o2 = p1.addOption("Yes, yammy!");
		VoteOption o3 = p1.addOption("I do not care");
		Poll p2 = bob.createPoll("Vim or Emacs");
		VoteOption o4 = p2.addOption("vim");
		VoteOption o5 = p2.addOption("emacs");
		alice.voteFor(o3);
		bob.voteFor(o1);
		eve.voteFor(o2);
		alice.voteFor(o4);

		return Arrays.asList(alice, bob, eve);
	}

	@Bean
	public CommandLineRunner startup(@Autowired EntityManagerFactory emf) {
		return args -> {
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			for (User u : populateTestDate()) {
				entityManager.persist(u);
			}

			entityManager.getTransaction().commit();
		};
	}




}
