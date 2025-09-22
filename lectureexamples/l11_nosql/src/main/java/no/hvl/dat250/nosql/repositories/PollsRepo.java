package no.hvl.dat250.nosql.repositories;

import no.hvl.dat250.nosql.entities.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollsRepo extends CrudRepository<Poll, Long> {
}
