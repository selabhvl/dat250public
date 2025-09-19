package no.hvl.dat250.springjpa.repositories;

import jakarta.persistence.LockModeType;
import no.hvl.dat250.springjpa.entities.Account;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, String> {


}
