package com.example.finalproject.service.card;

import com.example.finalproject.entity.transaction.CardTransaction;
import com.example.finalproject.request.card.CreateDebitCardRequest;
import com.example.finalproject.request.transaction.CardTransactionRequest;
import com.example.finalproject.request.transaction.TransactionDate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface DebitCardService {

    ResponseEntity<Object> createDebitCard(CreateDebitCardRequest request);

    ResponseEntity<Object> withdrawBalance(CardTransactionRequest request) throws IOException;

    ResponseEntity<Object> addBalance(CardTransactionRequest request) throws IOException;

    ResponseEntity<Object> deleteDebitByCardNumber(String cardNumber) throws IOException;

    List<CardTransaction> findTransactionDateBetweenAndCardNo(TransactionDate date, String cardNo) throws Exception;
}
