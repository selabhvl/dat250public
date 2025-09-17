package no.hvl.data250.springjpa.repositories;

import no.hvl.data250.springjpa.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
}
