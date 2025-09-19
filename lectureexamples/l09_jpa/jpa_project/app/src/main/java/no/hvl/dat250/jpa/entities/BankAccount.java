package no.hvl.dat250.jpa.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class BankAccount {

    @Id
    private String accountNo;

    @Basic(optional = false)
    @Column(columnDefinition = "NUMERIC(10,2) CHECK (balance >= 0)")
    private BigDecimal balance;


    public BankAccount(String accountNo, BigDecimal balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public BankAccount() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void transfer(BankAccount target, BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
        target.balance = target.balance.add(amount);
    }
}
