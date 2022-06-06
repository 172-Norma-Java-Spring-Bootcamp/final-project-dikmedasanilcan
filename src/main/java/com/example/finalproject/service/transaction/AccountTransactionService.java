package com.example.finalproject.service.transaction;


import java.util.*;
import com.example.finalproject.entity.account.Account;
import com.example.finalproject.entity.transaction.AccountTransaction;
import com.example.finalproject.request.transaction.AccountTransactionRequest;
import com.example.finalproject.request.transaction.DebtOnAccountRequest;
import com.example.finalproject.request.transaction.EndTransactionDate;

import com.example.finalproject.request.transaction.TransactionDate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.text.ParseException;

public interface AccountTransactionService {

    public <T extends Account> ResponseEntity<Object> balanceOnAccount(T a, String transactionType, AccountTransactionRequest request) throws IOException;
    public <T extends Account> ResponseEntity<Object> debtOnAccount(T a, DebtOnAccountRequest request) throws IOException;
    public <T extends Account> List<AccountTransaction> findAccountTransactionByDateAndAccountNumber(T a,TransactionDate date,String accountNumber) throws Exception;
    public <T extends Account> ResponseEntity<Object> withDrawAllMoney(T a,String accountNumber) throws IOException;
}
