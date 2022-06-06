package com.example.finalproject.service.impl.transaction;

import com.example.finalproject.domain.CurrencyOperation;
import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.account.Account;
import com.example.finalproject.entity.transaction.AccountTransaction;
import com.example.finalproject.entity.transaction.CardTransaction;
import com.example.finalproject.entity.transaction.TransferTransaction;
import com.example.finalproject.repository.transaction.AccountTransactionRepository;
import com.example.finalproject.repository.transaction.CardTransactionRepository;
import com.example.finalproject.repository.transaction.TransferTransactionRepository;
import com.example.finalproject.request.transaction.AccountTransactionRequest;
import com.example.finalproject.request.transaction.CardTransactionRequest;
import com.example.finalproject.request.transaction.DebtOnAccountRequest;
import com.example.finalproject.request.transaction.TransferTransactionRequest;
import com.example.finalproject.service.transaction.SaveTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SaveTransactionServiceImpl implements SaveTransactionService {

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private TransferTransactionRepository transferTransactionRepository;


    public void saveBalanceOnAccount(String txnType, Account account, AccountTransactionRequest request){


        AccountTransaction accountTransaction = new AccountTransaction(
                request.getAccountNumber(),
                account.getCurrencyType().toString(),
                request.getAmount(),
                txnType,
                new Timestamp(System.currentTimeMillis())
        );

        accountTransaction.setCustomer(account.getCustomer());
        accountTransactionRepository.save(accountTransaction);
    }

    @Override
    public void saveDebtOnAccount(Account account, DebtOnAccountRequest request, double txnAmount){

        AccountTransaction accountTransaction = new AccountTransaction(
                account.getAccountNumber(),
                account.getCurrencyType().toString(),
                txnAmount,
                "DebtPayment",
                new Timestamp(System.currentTimeMillis())
        );

        accountTransaction.setCustomer(account.getCustomer());
        accountTransactionRepository.save(accountTransaction);

    }
    @Override
    public void saveCreditCardTransaction(CardTransactionRequest request, Customer customer, String txnType){

        CardTransaction creditCardTransaction = new CardTransaction(
                                                request.getAmount(),
                                                txnType,
                                                new Timestamp(System.currentTimeMillis()),
                                                request.getCardNumber(),
                                                "CreditCard",
                                                "TRY");

        creditCardTransaction.setCustomer(customer);
        cardTransactionRepository.save(creditCardTransaction);

    }
    @Override
    public void saveDebitCardTransaction(CardTransactionRequest request, Account account, String txnType,double txnAmount){

        double amount=0;

        if(txnType.equals("DebtPayment"))
            amount=txnAmount;

        else
            amount=request.getAmount();


        CardTransaction debitCardTransaction = new CardTransaction(
                amount,
                txnType,
                new Timestamp(System.currentTimeMillis()),
                request.getCardNumber(),
                "DebitCard",
                account.getCurrencyType().toString());

        debitCardTransaction.setCustomer(account.getCustomer());
        cardTransactionRepository.save(debitCardTransaction);

    }

    @Override
    public void saveTransferTransaction(TransferTransactionRequest request,Account sender,Account receiver,String txnType,CurrencyOperation currencyClass){


        double transactionRate = currencyClass.getRates().get(receiver.getCurrencyType().toString()) /currencyClass.getRates().get(sender.getCurrencyType().toString()) ;

        double receiverAmount = transactionRate*request.getAmount();
        double senderAmount=request.getAmount();

        double amount=0;

        if(txnType.equals("SendMoney"))
            amount=senderAmount;

        if(txnType.equals("ReceiveMoney"))
            amount=receiverAmount;


        TransferTransaction transaction =  new TransferTransaction(
                request.getFromIbanNo(),
                request.getToIbanNo(),
                sender.getCurrencyType().toString(),
                receiver.getCurrencyType().toString(),
                amount,
                txnType,
                new Timestamp(System.currentTimeMillis()));

        if(txnType.equals("SendMoney"))
            transaction.setCustomer(sender.getCustomer());
        else
            transaction.setCustomer(receiver.getCustomer());

        transferTransactionRepository.save(transaction);
    }
    @Override
    public void saveTransferTransactionBetweenAccounts(Account account,double amount,String txnType){


        AccountTransaction accountTransaction =new AccountTransaction(
                account.getAccountNumber(),
                account.getCurrencyType().toString(),
                amount,
                txnType,
                new Timestamp(System.currentTimeMillis())
        );
        accountTransaction.setCustomer(account.getCustomer());
        accountTransactionRepository.save(accountTransaction);

    }

    @Override
    public void saveAutoPaymentTransaction(Account account,double amount,String txnType){


        AccountTransaction accountTransaction =new AccountTransaction(
                account.getAccountNumber(),
                account.getCurrencyType().toString(),
                amount,
                txnType,
                new Timestamp(System.currentTimeMillis())
        );
        accountTransaction.setCustomer(account.getCustomer());
        accountTransactionRepository.save(accountTransaction);



    }

}
