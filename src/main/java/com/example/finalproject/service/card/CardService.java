package com.example.finalproject.service.card;

import com.example.finalproject.entity.card.CreditCard;
import com.example.finalproject.entity.card.DebitCard;
import com.example.finalproject.entity.transaction.CardTransaction;
import com.example.finalproject.request.card.CreateCreditCardRequest;
import com.example.finalproject.request.card.CreateDebitCardRequest;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface CardService {

    public ResponseEntity<Object> createCreditCard(CreditCard creditCard, CreateCreditCardRequest request);
    public ResponseEntity<Object> createDebitCard(DebitCard debitCard, CreateDebitCardRequest request);

}
