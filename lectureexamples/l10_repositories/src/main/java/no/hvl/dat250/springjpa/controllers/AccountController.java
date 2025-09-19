package no.hvl.dat250.springjpa.controllers;

import jakarta.persistence.EntityManager;
import no.hvl.dat250.springjpa.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private EntityManager em;


    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        Account account = em.find(Account.class, accountId);
        return account;
    }

    @PutMapping("/{accountId}")
    @Transactional
    public Account updateAccount(@PathVariable String accountId, @RequestBody Account account) {
        Account storedAccount = em.find(Account.class, accountId);
        storedAccount.setBalance(account.getBalance());
        return storedAccount;
    }

}
