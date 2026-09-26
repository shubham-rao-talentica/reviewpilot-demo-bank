package com.reviewpilot.bank.transfer;

import com.reviewpilot.bank.account.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public Transaction transfer(@RequestBody TransferRequest request) {
        return transferService.transfer(
                request.getFromAccountNumber(),
                request.getToAccountNumber(),
                request.getAmount(),
                request.getPin());
    }

    @GetMapping("/search")
    public List<Transaction> search(@RequestParam String ownerName) {
        return transferService.searchByOwnerName(ownerName);
    }

    @GetMapping("/account/{accountNumber}/exists")
    public boolean accountExists(@PathVariable String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).isPresent();
    }
}
