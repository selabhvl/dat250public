package no.hvl.dat250.springjpa.controllers;

import no.hvl.dat250.springjpa.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TransferController {

    @Autowired
    private TransferService service;

    @PostMapping("/transfers")
    public void doTransfer(
            @RequestParam String fromAccount,
            @RequestParam String toAccount,
            @RequestParam BigDecimal amount) {
        service.doTransfer(fromAccount, toAccount, amount);
    }
}
