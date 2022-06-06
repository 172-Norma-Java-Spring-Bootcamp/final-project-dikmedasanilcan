package com.example.finalproject.service.account;

import com.example.finalproject.entity.account.Account;
import com.example.finalproject.request.account.CreateAccountRequest;
import com.example.finalproject.request.account.CreateSavingAccountRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AccountService {

    public  <T extends Account> ResponseEntity<Object> createAccount(T a, CreateAccountRequest request) throws IOException;
    public <T extends Account> ResponseEntity<Object> deleteAccount(T a, String accountNumber);
    public <T extends Account> ResponseEntity<Object> createSavingAccount(T a, CreateSavingAccountRequest request) throws IOException;

}
