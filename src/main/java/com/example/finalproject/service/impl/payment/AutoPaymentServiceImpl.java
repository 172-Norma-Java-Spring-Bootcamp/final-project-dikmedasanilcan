package com.example.finalproject.service.impl.payment;

import com.example.finalproject.entity.account.Account;
import com.example.finalproject.entity.account.DepositAccount;
import com.example.finalproject.entity.account.SavingAccount;
import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.payment.AutoPayment;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.account.DepositAccountRepository;
import com.example.finalproject.service.currency.CurrencyService;
import com.example.finalproject.service.payment.AutoPaymentService;
import com.example.finalproject.service.transaction.SaveTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class AutoPaymentServiceImpl implements AutoPaymentService {

    @Autowired
    private DepositAccountRepository depositAccountRepository;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SaveTransactionService saveTransactionService;

    public void getAutoPayment() throws IOException {

        List<Account> depositAccounts=depositAccountRepository.findByAccountType("DepositAccount");

        for (int i=0;i<depositAccounts.size();i++){

            DepositAccount depositAccount = depositAccountRepository.findByAccountId(depositAccounts.get(i).getAccountId());

            for(int j=0;j<depositAccount.getAutoPayments().size();j++){

                AutoPayment autoPayment =depositAccount.getAutoPayments().get(j);

                Customer customer = depositAccount.getCustomer();

                Calendar calendar = Calendar.getInstance();
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

                if(currentDay==autoPayment.getDay()){

                    double accountBalance=currencyService.setCurrencyAsset(autoPayment.getAmount(),depositAccount);

                    if(autoPayment.getAmount()<=accountBalance){

                        double withdrawAmountToDeposit=currencyService.setAccountBalance(autoPayment.getAmount(),depositAccount);
                        depositAccount.setBalance(depositAccount.getBalance()-withdrawAmountToDeposit);
                        customer.setAsset(customer.getAsset()-autoPayment.getAmount());

                        customerRepository.save(customer);
                        depositAccountRepository.save(depositAccount);

                        saveTransactionService.saveAutoPaymentTransaction(depositAccount,withdrawAmountToDeposit,"AutoPayment");
                    }

                }
            }
        }




    }



}
