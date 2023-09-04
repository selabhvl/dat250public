package no.hvl.dat250.springBoot;

import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<QuoteEntity, Long> {
}
