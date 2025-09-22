package no.hvl.dat250.nosql.repositories;

import no.hvl.dat250.nosql.entities.VoteOption;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VoteOptionRepo extends CrudRepository<VoteOption, UUID> {
}
