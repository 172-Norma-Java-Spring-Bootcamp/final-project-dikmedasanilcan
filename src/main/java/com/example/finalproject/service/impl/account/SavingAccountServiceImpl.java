package com.example.finalproject.service.impl.account;


import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.account.Account;
import com.example.finalproject.entity.account.DepositAccount;
import com.example.finalproject.entity.account.SavingAccount;
import com.example.finalproject.entity.transaction.AccountTransaction;
import com.example.finalproject.repository.account.SavingAccountRepository;
import com.example.finalproject.repository.interest.DailyInterestRepository;
import com.example.finalproject.repository.interest.InterestRepository;
import com.example.finalproject.request.account.CreateSavingAccountRequest;
import com.example.finalproject.request.transaction.AccountTransactionRequest;
import com.example.finalproject.request.transaction.TransactionDate;
import com.example.finalproject.request.transaction.TransferTransactionRequest;
import com.example.finalproject.service.account.AccountService;
import com.example.finalproject.service.account.SavingAccountService;
import com.example.finalproject.service.currency.CurrencyService;
import com.example.finalproject.service.impl.interest.DailyInterestServiceImpl;
import com.example.finalproject.service.transaction.AccountTransactionService;
import com.example.finalproject.service.transaction.SaveTransactionService;
import com.example.finalproject.service.transaction.TransferTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

    @Autowired
    private final AccountService accountService;

    @Autowired
    private final AccountTransactionService accountTransactionService;

    @Autowired
    private final TransferTransactionService transactionService;

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    private DailyInterestServiceImpl dailyInterestService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private SaveTransactionService saveTransactionService;


    public SavingAccountServiceImpl(AccountService accountService, AccountTransactionService accountTransactionService, TransferTransactionService transactionService) {
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<Object> createAccount(CreateSavingAccountRequest request) throws IOException {
        SavingAccount savingAccount = new SavingAccount();
        return accountService.createSavingAccount(savingAccount,request);
    }

    @Override
    public ResponseEntity<Object> addBalance(AccountTransactionRequest request) throws IOException {
        SavingAccount savingAccount = new SavingAccount();
        String type="AddBalance";
        return accountTransactionService.balanceOnAccount(savingAccount,type,request);
    }

    @Override
    public ResponseEntity<Object> withdrawBalance(AccountTransactionRequest request) throws IOException {
        SavingAccount savingAccount = new SavingAccount();
        String type="WithdrawBalance";
        return accountTransactionService.balanceOnAccount(savingAccount,type,request);
    }

    @Override
    public ResponseEntity<Object> sendMoneyToDeposit(TransferTransactionRequest request)throws IOException {

        SavingAccount sender = new SavingAccount();
        DepositAccount receiver = new DepositAccount();

        return transactionService.sendMoney(sender,receiver,request);

    }

    @Override
    public ResponseEntity<Object> sendMoneyToSaving(TransferTransactionRequest request) throws IOException {
        SavingAccount sender = new SavingAccount();
        SavingAccount receiver = new SavingAccount();

        return transactionService.sendMoney(sender,receiver,request);
    }

    @Override
    public ResponseEntity<Object> deleteAccount(String accountNumber) {
        SavingAccount savingAccount = new SavingAccount();
        return accountService.deleteAccount(savingAccount,accountNumber);
    }

    @Override
    public List<AccountTransaction> findTransaction(TransactionDate date, String accountNumber) throws Exception {
        SavingAccount savingAccount = new SavingAccount();
        return accountTransactionService.findAccountTransactionByDateAndAccountNumber(savingAccount,date,accountNumber);
    }

    @Override
    public ResponseEntity<Object> withDrawAllMoney(String accountNumber) throws IOException {

        SavingAccount savingAccount = new SavingAccount();
        return accountTransactionService.withDrawAllMoney(savingAccount,accountNumber);

    }
    @Scheduled(cron="0 0 0 * * ?")
    public void savingAccountInterestChecker() throws IOException {

        List<Account> savingAccounts=savingAccountRepository.findByAccountType("SavingAccount");

        for(int i=0;i<savingAccounts.size();i++){

            SavingAccount savingAccount = savingAccountRepository.findByAccountId(savingAccounts.get(i).getAccountId());

            if(savingAccount.getRemainingDay()>1 && savingAccount.isActive()){
                savingAccount.setRemainingDay(savingAccount.getRemainingDay()-1);
                savingAccountRepository.save(savingAccount);
            }

            else if(savingAccount.getRemainingDay()==1 && savingAccount.isActive()){

                savingAccount.setRemainingDay(savingAccount.getRemainingDay()-1);
                savingAccount.setActive(false);

                if(savingAccount.getBalance()>=savingAccount.getStarterBalance()){

                    double addBalance=dailyInterestService.interestCalculator(savingAccount.getCurrencyType().toString(),savingAccount.getStarterBalance(),savingAccount.getDay());

                    AccountTransactionRequest request = new AccountTransactionRequest();
                    request.setAccountNumber(savingAccount.getAccountNumber());
                    request.setAmount(addBalance);


                    savingAccount.setBalance(savingAccount.getBalance()+addBalance);
                    currencyService.setCustomerAsset(addBalance,savingAccount,"AddBalance");
                    saveTransactionService.saveBalanceOnAccount("AddBalanceFromSavingInterest",savingAccount,request);

                    savingAccountRepository.save(savingAccount);
                }
                else{

                   savingAccountRepository.save(savingAccount);
                }


            }
        }
    }
}





