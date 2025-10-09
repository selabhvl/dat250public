package no.hvl.dat250.containers.repositories;

import no.hvl.dat250.containers.entities.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollsRepo extends CrudRepository<Poll, Long> {
}
