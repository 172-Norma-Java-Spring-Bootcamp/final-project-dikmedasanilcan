package com.example.finalproject.service.transaction;

import com.example.finalproject.entity.account.Account;
import com.example.finalproject.request.transaction.TransferTransactionRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface TransferTransactionService {

    public <T extends Account> ResponseEntity<Object> sendMoney(T a, T b, TransferTransactionRequest request) throws IOException;


}