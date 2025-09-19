package no.hvl.dat250.springjpa.services;

import no.hvl.dat250.springjpa.repositories.AccountRepository;
import no.hvl.dat250.springjpa.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    @Autowired
    private AccountRepository repository;

    public void doTransfer(String fromAccount, String toAccount, BigDecimal amount) {
        Account from = repository.findById(fromAccount).get();
        Account to = repository.findById(toAccount).get();
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        repository.save(from);
        repository.save(to);
    }


}
