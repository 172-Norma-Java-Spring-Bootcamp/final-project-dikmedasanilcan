package com.example.finalproject.repository.transaction;

import com.example.finalproject.entity.transaction.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {

    List<AccountTransaction> findByAccountNumber(String accountNumber);

    List<AccountTransaction> findByDateBetweenAndAccountNumber(Date start, Date end,String accountNumber);




}