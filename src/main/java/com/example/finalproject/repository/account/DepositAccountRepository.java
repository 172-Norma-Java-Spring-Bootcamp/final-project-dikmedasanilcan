package com.example.finalproject.repository.account;

import com.example.finalproject.entity.account.DepositAccount;
import com.example.finalproject.entity.account.SavingAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositAccountRepository extends AccountRepository {

    DepositAccount findByAccountNumber(String accountNumber);
    DepositAccount findByIbanNo(String ibanNo);
    DepositAccount findByCustomerId(long id);
    DepositAccount findByAccountId(long id);




}