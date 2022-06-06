package com.example.finalproject.service.impl.card;


import com.example.finalproject.entity.credit.CreditPoint;
import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.account.DepositAccount;
import com.example.finalproject.entity.card.Card;
import com.example.finalproject.entity.card.CreditCard;
import com.example.finalproject.entity.card.DebitCard;
import com.example.finalproject.entity.operation.OperationType;
import com.example.finalproject.entity.operation.SystemOperations;
import com.example.finalproject.generator.CardNumberGenerator;
import com.example.finalproject.generator.SecurityCodeGenerator;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.SystemOperationsRepository;
import com.example.finalproject.repository.account.DepositAccountRepository;
import com.example.finalproject.repository.card.CreditCardRepository;
import com.example.finalproject.repository.card.DebitCardRepository;
import com.example.finalproject.repository.credit.CreditPointRepository;
import com.example.finalproject.request.card.CreateCreditCardRequest;
import com.example.finalproject.request.card.CreateDebitCardRequest;
import com.example.finalproject.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private DepositAccountRepository depositAccountRepository;

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private SystemOperationsRepository systemOperationsRepository;

    @Autowired
    private CreditPointRepository creditPointRepository;


    CardNumberGenerator cardNumberGenerator=new CardNumberGenerator();

    SecurityCodeGenerator securityCodeGenerator = new SecurityCodeGenerator();

    public CardServiceImpl(CustomerRepository customerRepository, CreditCardRepository creditCardRepository, DepositAccountRepository depositAccountRepository, DebitCardRepository debitCardRepository) {
        this.customerRepository = customerRepository;
        this.creditCardRepository = creditCardRepository;
        this.depositAccountRepository = depositAccountRepository;
        this.debitCardRepository = debitCardRepository;
    }

    public CardServiceImpl() {

    }

    @Override
    public ResponseEntity<Object> createCreditCard(CreditCard creditCard, CreateCreditCardRequest request){

        Customer customer =customerRepository.findById(request.getCustomer_id());

        if(customer==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

        boolean isUnique=true;
        while (isUnique){

            String cardNumber = cardNumberGenerator.generateCardNumber();

            if(creditCardRepository.findByCardNumber(cardNumber)!=null){
                continue;
            }

            creditCard.setCardNumber(cardNumber);
            isUnique=false;
        }

        CreditPoint creditPoint = creditPointRepository.findByRanking(customer.getCreditPoint());

        if(request.getCreditLimit()>creditPoint.getMaxCreditLimit()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer credit point is:"+customer.getCreditPoint()+"Max credit limit can be "+creditPoint.getMaxCreditLimit());

        }


        creditCard.setCardLimit(request.getCreditLimit());
        creditCard.setCardType(creditCard.getClass().getSimpleName());
        creditCard.setCurrentLimit(request.getCreditLimit());
        creditCard.setExpiredDate(this.expiredDate());
        creditCard.setUsable(true);
        creditCard.setCustomer(customer);
        creditCard.setSecurityNumber(securityCodeGenerator.generateSecurityCode());


        creditCardRepository.save(creditCard);



        SystemOperations systemOperations = new SystemOperations(0L, OperationType.CREATE_CREDIT_CARD.toString(), new Timestamp(System.currentTimeMillis()));
        systemOperations.setCustomer(customer);
        systemOperationsRepository.save(systemOperations);

        return ResponseEntity.status(HttpStatus.OK).body("Credit card created");
    }

    @Override
    public ResponseEntity<Object> createDebitCard(DebitCard debitCard, CreateDebitCardRequest request) {

        DepositAccount depositAccount = depositAccountRepository.findByAccountNumber(request.getAccountNumber());

        if(depositAccount==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");

        }
        boolean isUnique=true;

        while (isUnique){

            String cardNumber = cardNumberGenerator.generateCardNumber();
            if(debitCardRepository.findByCardNumber(cardNumber)!=null){
                continue;
            }
            debitCard.setCardNumber(cardNumber);
            isUnique=false;
        }


        debitCard.setCardType(debitCard.getClass().getSimpleName());
        debitCard.setExpiredDate(this.expiredDate());
        debitCard.setDepositAccount(depositAccount);
        debitCard.setUsable(true);
        debitCard.setSecurityNumber(securityCodeGenerator.generateSecurityCode());



        debitCardRepository.save(debitCard);


        SystemOperations systemOperations = new SystemOperations(0L, OperationType.CREATE_DEBIT_CARD.toString(), new Timestamp(System.currentTimeMillis()));
        systemOperations.setCustomer(depositAccount.getCustomer());
        systemOperationsRepository.save(systemOperations);


        return ResponseEntity.status(HttpStatus.OK).body("Debit card created");
    }




    public String expiredDate(){

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR)+5;
        String expiredDate= month +"/"+ year;

        return expiredDate;

    }


    public void isUsable(){


        Calendar calendar = Calendar.getInstance();
        int month =calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR)+1;

        String montString = String.valueOf(month);
        String yearString = String.valueOf(year);

        List<Card> creditCards=creditCardRepository.findAll();

        for (Card creditCard : creditCards) {

            if (creditCard.getExpiredDate().equals(montString+"/"+yearString)) {

                creditCard.setUsable(false);
                creditCardRepository.save(creditCard);
            }

        }
    }



}

