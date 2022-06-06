package com.example.finalproject.service.transaction;


import com.example.finalproject.entity.account.Account;
import com.example.finalproject.entity.card.Card;
import com.example.finalproject.entity.transaction.CardTransaction;
import com.example.finalproject.request.transaction.CardTransactionRequest;
import com.example.finalproject.request.transaction.DebtOnCardRequest;
import com.example.finalproject.request.transaction.TransactionDate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface CardTransactionService {

    public ResponseEntity<Object> withdrawCreditCard(CardTransactionRequest transaction);

    public ResponseEntity<Object> debitCardTransaction(CardTransactionRequest transaction,String transactionType) throws IOException;

    public <T extends Account> ResponseEntity<Object> debtOnCard(T a, DebtOnCardRequest request) throws IOException;

    public <T extends Card> List<CardTransaction> findByDateBetweenAndCardNo(T a,TransactionDate date, String cardNo) throws Exception;


}