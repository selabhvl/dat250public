package no.hvl.data250.springjpa.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    private String accountNo;

    @Basic(optional = false)
    @Column(columnDefinition = "NUMERIC(10, 2) CHECK (amount > 0)")
    private BigDecimal amount;

    public Account(String accountNo, BigDecimal amount) {
        this.accountNo = accountNo;
        this.amount = amount;
    }

    public Account() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
