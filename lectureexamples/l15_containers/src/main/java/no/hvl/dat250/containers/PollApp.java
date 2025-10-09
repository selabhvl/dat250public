package no.hvl.dat250.containers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import no.hvl.dat250.containers.entities.Poll;
import no.hvl.dat250.containers.entities.User;
import no.hvl.dat250.containers.entities.VoteOption;
import no.hvl.dat250.containers.repositories.PollsRepo;
import no.hvl.dat250.containers.repositories.UserRepo;
import no.hvl.dat250.containers.repositories.VoteOptionRepo;
import no.hvl.dat250.containers.repositories.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication()
public class PollApp {

	public static void main(String[] args) {
		SpringApplication.run(PollApp.class, args);
	}

//	@Bean
//	public CommandLineRunner startup(
//			@Autowired UserRepo userRepo,
//			@Autowired PollsRepo pollsRepo,
//			@Autowired VoteOptionRepo voteOptionRepo,
//			@Autowired VoteRepo voteRepo) {
//		return args -> {
//			User alice = userRepo.save(new User("alice", "alice@online.com"));
//			User bob = userRepo.save(new User("bob", "bob@bob.net"));
//			User eve = userRepo.save(new User("eve", "eve@mail.org"));
//			Poll p1 = pollsRepo.save(alice.createPoll("Pineapple on Pizza"));
//			VoteOption o1 = voteOptionRepo.save(p1.addOption("Mamma mia: Nooooo!"));
//			VoteOption o2 = voteOptionRepo.save(p1.addOption("Yes, yammy!"));
//			VoteOption o3 = voteOptionRepo.save(p1.addOption("I do not care"));
//			Poll p2 = pollsRepo.save(bob.createPoll("Vim or Emacs"));
//			VoteOption o4 = voteOptionRepo.save(p2.addOption("vim"));
//			VoteOption o5 = voteOptionRepo.save(p2.addOption("emacs"));
//			voteRepo.save(alice.voteFor(o3));
//			voteRepo.save(bob.voteFor(o1));
//			voteRepo.save(eve.voteFor(o2));
//			voteRepo.save(alice.voteFor(o4));
//		};
//	}




}
