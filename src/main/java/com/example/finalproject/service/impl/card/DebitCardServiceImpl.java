package com.example.finalproject.service.impl.card;


import com.example.finalproject.entity.card.CreditCard;
import com.example.finalproject.entity.card.DebitCard;
import com.example.finalproject.entity.operation.OperationType;
import com.example.finalproject.entity.operation.SystemOperations;
import com.example.finalproject.entity.transaction.CardTransaction;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.SystemOperationsRepository;
import com.example.finalproject.repository.card.DebitCardRepository;
import com.example.finalproject.request.card.CreateDebitCardRequest;
import com.example.finalproject.request.transaction.CardTransactionRequest;
import com.example.finalproject.request.transaction.TransactionDate;
import com.example.finalproject.service.card.CardService;
import com.example.finalproject.service.card.DebitCardService;
import com.example.finalproject.service.transaction.CardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardTransactionService cardTransactionService;

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SystemOperationsRepository systemOperationsRepository;

    @Override
    public ResponseEntity<Object> createDebitCard(CreateDebitCardRequest request) {

        DebitCard debitCard = new DebitCard();
        return cardService.createDebitCard(debitCard,request);
    }
    @Override
    public ResponseEntity<Object> withdrawBalance(CardTransactionRequest request) throws IOException {
        return cardTransactionService.debitCardTransaction(request,"WithdrawBalance");
    }

    @Override
    public ResponseEntity<Object> addBalance(CardTransactionRequest request) throws IOException {
        return cardTransactionService.debitCardTransaction(request,"AddBalance");
    }

    @Override
    public ResponseEntity<Object> deleteDebitByCardNumber(String cardNumber) throws IOException {

        DebitCard debitCard = debitCardRepository.findByCardNumber(cardNumber);

        if(debitCard==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debit card not found");
        }

        debitCardRepository.delete(debitCard);


        SystemOperations systemOperations = new SystemOperations(0L, OperationType.DELETE_DEBIT_CARD.toString(), new Timestamp(System.currentTimeMillis()));
        systemOperationsRepository.save(systemOperations);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debit card deleted");
    }

    @Override
    public List<CardTransaction> findTransactionDateBetweenAndCardNo(TransactionDate date, String cardNo) throws Exception {
        DebitCard debitCard = new DebitCard();
        return cardTransactionService.findByDateBetweenAndCardNo(debitCard,date,cardNo);
    }


}
