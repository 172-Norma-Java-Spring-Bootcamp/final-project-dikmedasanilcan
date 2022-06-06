package com.example.finalproject.service.card;

import com.example.finalproject.entity.transaction.CardTransaction;
import com.example.finalproject.request.card.CreateCreditCardRequest;
import com.example.finalproject.request.transaction.CardTransactionRequest;
import com.example.finalproject.request.transaction.DebtOnAccountRequest;
import com.example.finalproject.request.transaction.DebtOnCardRequest;
import com.example.finalproject.request.transaction.TransactionDate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface CreditCardService {

    public ResponseEntity<Object> createCredit(CreateCreditCardRequest request);

    ResponseEntity<Object> withdrawBalance(CardTransactionRequest request) throws IOException;

    ResponseEntity<Object> debtInquiry(String cardNumber);

    ResponseEntity<Object> debtOnDepositAccount(DebtOnAccountRequest request) throws IOException;

    ResponseEntity<Object> debtOnSavingAccount(DebtOnAccountRequest request) throws IOException;

    ResponseEntity<Object> debtOnDebitCard(DebtOnCardRequest request) throws IOException;

    ResponseEntity<Object> deleteCreditCardByCardNumber(String cardNumber);

    List<CardTransaction> findByTransactionCardTypeAndCardNo(String cardNo);

    List<CardTransaction> findTransactionDateBetweenAndCardNo(TransactionDate date,String cardNo) throws Exception;

    public ResponseEntity<Object> getMaxCreditLimit(long customerId);
}
