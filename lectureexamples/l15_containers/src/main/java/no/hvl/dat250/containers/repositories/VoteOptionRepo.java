package no.hvl.dat250.containers.repositories;

import no.hvl.dat250.containers.entities.VoteOption;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VoteOptionRepo extends CrudRepository<VoteOption, UUID> {
}
