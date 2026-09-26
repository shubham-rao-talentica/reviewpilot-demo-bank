package com.reviewpilot.bank.transfer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;

    protected Transaction() {
    }

    public Transaction(String reference, String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        this.reference = reference;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
