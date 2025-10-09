package no.hvl.dat250.containers.repositories;

import no.hvl.dat250.containers.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
