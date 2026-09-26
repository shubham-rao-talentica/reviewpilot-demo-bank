package com.reviewpilot.bank.transfer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction WHERE from_account_number = :accountNumber OR to_account_number = :accountNumber", nativeQuery = true)
    List<Transaction> findAllForAccount(@Param("accountNumber") String accountNumber);
}
