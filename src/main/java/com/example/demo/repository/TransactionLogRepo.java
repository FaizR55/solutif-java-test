package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionLog;

@Repository
public interface TransactionLogRepo extends JpaRepository<TransactionLog, Long>  {
    
    @Query("SELECT t FROM TransactionLog t WHERE t.success = true")
    Page<TransactionLog> getSuccessTransactions(PageRequest pagination);

    @Query("SELECT t FROM TransactionLog t WHERE t.success = true AND t.account_to = :accountNumber")
    List<TransactionLog> getSuccessTransactionsFromAccount(@Param("accountNumber") String accountNumber);
    
}
