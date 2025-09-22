package no.hvl.dat250.nosql.repositories;

import no.hvl.dat250.nosql.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends CrudRepository<User, Long> {
}
