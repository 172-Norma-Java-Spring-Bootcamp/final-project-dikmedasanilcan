package com.example.finalproject.service.account;


import com.example.finalproject.entity.transaction.AccountTransaction;
import com.example.finalproject.request.account.CreateAccountRequest;
import com.example.finalproject.request.transaction.AccountTransactionRequest;
import com.example.finalproject.request.transaction.TransactionDate;
import com.example.finalproject.request.transaction.TransferTransactionRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface DepositAccountService {


    public ResponseEntity<Object> createAccount(CreateAccountRequest request) throws IOException;
    public ResponseEntity<Object> addBalance(AccountTransactionRequest request) throws IOException;
    public ResponseEntity<Object> withdrawBalance(AccountTransactionRequest request) throws IOException;

    public ResponseEntity<Object> sendMoneyToDeposit(TransferTransactionRequest request) throws IOException;
    public ResponseEntity<Object> sendMoneyToSaving(TransferTransactionRequest request) throws IOException;
    public ResponseEntity<Object> deleteAccount(String accountNumber);
    public List<AccountTransaction> findTransaction(TransactionDate date, String accountNumber) throws Exception;
    public ResponseEntity<Object> withDrawAllMoney(String accountNumber) throws IOException;
    public ResponseEntity<Object> autoPaymentRequest(String accountNumber,double amount);

}
