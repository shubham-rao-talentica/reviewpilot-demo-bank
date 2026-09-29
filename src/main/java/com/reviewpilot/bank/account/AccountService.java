package com.reviewpilot.bank.account;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse getByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NoSuchElementException("No account found: " + accountNumber));
        return AccountResponse.from(account);
    }

    public boolean isLowBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).get();
        return account.getBalance().doubleValue() < 100;
    }
}
