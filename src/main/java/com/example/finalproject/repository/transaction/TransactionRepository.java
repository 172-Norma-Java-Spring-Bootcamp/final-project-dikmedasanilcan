package com.example.finalproject.repository.transaction;

import com.example.finalproject.entity.transaction.AccountTransaction;
import com.example.finalproject.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TransactionRepository extends JpaRepository<Transaction,Long> {


}
