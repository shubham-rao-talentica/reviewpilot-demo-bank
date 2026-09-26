package com.reviewpilot.bank.account;

import java.math.BigDecimal;

public record AccountResponse(String accountNumber, String ownerName, BigDecimal balance) {

    public static AccountResponse from(Account account) {
        return new AccountResponse(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }
}
