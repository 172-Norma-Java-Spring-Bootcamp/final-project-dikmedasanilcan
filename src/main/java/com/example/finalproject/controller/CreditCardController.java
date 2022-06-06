package com.example.finalproject.controller;

import com.example.finalproject.entity.transaction.AccountTransaction;
import com.example.finalproject.entity.transaction.CardTransaction;
import com.example.finalproject.request.card.CreateCreditCardRequest;
import com.example.finalproject.request.transaction.CardTransactionRequest;
import com.example.finalproject.request.transaction.DebtOnAccountRequest;
import com.example.finalproject.request.transaction.DebtOnCardRequest;
import com.example.finalproject.request.transaction.TransactionDate;
import com.example.finalproject.service.card.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customer/creditCard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;


    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CreateCreditCardRequest request){
        return creditCardService.createCredit(request);
    }

    @PostMapping("/withDrawBalance")
    public ResponseEntity<Object> withdrawBalance(@RequestBody CardTransactionRequest request) throws IOException {

        try {
            return creditCardService.withdrawBalance(request);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This credit card already used in another transaction.");
        }
    }

    @PostMapping("/debtPayment/deposit")
    public ResponseEntity<Object> debtOnDeposit(@RequestBody DebtOnAccountRequest request) throws IOException {

        try {
            return creditCardService.debtOnDepositAccount(request);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This credit card or deposit account already used in another transaction.");
        }

    }
    @PostMapping("/debtPayment/saving")
    public ResponseEntity<Object> debtOnSaving(@RequestBody DebtOnAccountRequest request) throws IOException {

        try {
            return creditCardService.debtOnSavingAccount(request);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This credit card or saving account already used in another transaction.");
        }
    }

    @PostMapping("/debtPayment/debitCard")
    public ResponseEntity<Object> debtOnDebit(@RequestBody DebtOnCardRequest request) throws IOException {

        try {
            return creditCardService.debtOnDebitCard(request);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This credit card or debit card already used in another transaction.");
        }
    }

    @GetMapping("/debtInquiry/{cardNumber}")
    public ResponseEntity<Object> debtInquiry(@PathVariable String cardNumber){

        return creditCardService.debtInquiry(cardNumber);
    }

    @DeleteMapping("/deleteByCardNumber/{cardNumber}")
    private ResponseEntity<Object> deleteById(@PathVariable String cardNumber){
        return creditCardService.deleteCreditCardByCardNumber(cardNumber);
    }
    @GetMapping("/list/allTransactions/{cardNumber}")
    public List<CardTransaction> findTransaction(@PathVariable String cardNumber){
        return creditCardService.findByTransactionCardTypeAndCardNo(cardNumber);
    }

    @PostMapping("/list/transaction/byDate/{cardNo}")
    public List<CardTransaction> findAccountTransactionByDate(@RequestBody TransactionDate date, @PathVariable String cardNo) throws Exception {

        try {
            return creditCardService.findTransactionDateBetweenAndCardNo(date,cardNo);
        }catch (Exception e){
            throw new Exception("Card not found");
        }
    }

    @GetMapping("/get/maxCreditLimit{customerId}")
    public ResponseEntity<Object> getMaxCreditLimit(@PathVariable long customerId){
        return creditCardService.getMaxCreditLimit(customerId);
    }

}
