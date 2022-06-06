package com.example.finalproject.service.impl.currency;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.finalproject.domain.CurrencyOperation;
import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.account.Account;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void setCustomerAsset(double amount, Account account,String txnType) throws IOException {

        ObjectMapper objectMapper =new ObjectMapper();
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=TRY");
        CurrencyOperation currencyClass =objectMapper.readValue(url,CurrencyOperation.class);

        Customer customer =customerRepository.findById(account.getCustomer().getId());

        double newCustomerAsset= amount/currencyClass.getRates().get(account.getCurrencyType().toString());

        if(txnType.equals("AddBalance")){

            customer.setAsset(customer.getAsset()+newCustomerAsset);
            customerRepository.save(customer);
        }

        if(txnType.equals("WithdrawBalance")){
            customer.setAsset(customer.getAsset()-newCustomerAsset);
            customerRepository.save(customer);
        }
    }

    @Override
    public double setCurrencyAsset(double amount, Account account) throws IOException {

        ObjectMapper objectMapper =new ObjectMapper();
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=TRY");
        CurrencyOperation currencyClass =objectMapper.readValue(url,CurrencyOperation.class);

        double currencyAsset =account.getBalance()/currencyClass.getRates()
                .get(account.getCurrencyType().toString());

        return currencyAsset;
    }

    @Override
    public double setAccountBalance(double amount, Account account) throws IOException {

        ObjectMapper objectMapper =new ObjectMapper();
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=TRY");
        CurrencyOperation currencyClass =objectMapper.readValue(url,CurrencyOperation.class);
        double accountBalance= amount*currencyClass.getRates().get(account.getCurrencyType().toString());

        return accountBalance;


    }


}
