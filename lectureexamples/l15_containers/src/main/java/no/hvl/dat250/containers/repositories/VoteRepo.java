package no.hvl.dat250.containers.repositories;

import no.hvl.dat250.containers.entities.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VoteRepo extends CrudRepository<Vote, UUID> {
}
