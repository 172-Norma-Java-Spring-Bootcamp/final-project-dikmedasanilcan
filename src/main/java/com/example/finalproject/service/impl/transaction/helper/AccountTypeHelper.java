package com.example.finalproject.service.impl.transaction.helper;

import com.example.finalproject.entity.account.Account;
import com.example.finalproject.entity.account.DepositAccount;
import com.example.finalproject.entity.account.SavingAccount;
import com.example.finalproject.repository.account.AccountRepository;
import com.example.finalproject.repository.account.DepositAccountRepository;
import com.example.finalproject.repository.account.SavingAccountRepository;
import com.example.finalproject.request.transaction.AccountTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeHelper {


    @Autowired
    private DepositAccountRepository depositAccountRepository;

    @Autowired
    private SavingAccountRepository savingAccountRepository;


    public <T extends Account> Account setAccountType(T a, String accountNumber){

        Account account=null;


        if(a instanceof DepositAccount){

            account=depositAccountRepository.findByAccountNumber(accountNumber);

            if(account==null)
                return null;
            else
                return account;

        }
        if(a instanceof SavingAccount){

            account=savingAccountRepository.findByAccountNumber(accountNumber);

            if(account==null)
                return null;
            else
                return account;
        }
        return null;

    }
    public <T extends Account> AccountRepository setAccountRepo(T a){

        if(a instanceof DepositAccount){

            return depositAccountRepository;
        }

        else
            return savingAccountRepository;
    }

    public <T extends Account> Account setAccountTypeByIban(T a, String ibanNo){

        Account account=null;


        if(a instanceof DepositAccount){

            account=depositAccountRepository.findByIbanNo(ibanNo);

            if(account==null)
                return null;
            else
                return account;

        }
        if(a instanceof SavingAccount){

            account=savingAccountRepository.findByIbanNo(ibanNo);

            if(account==null)
                return null;
            else
                return account;
        }
        return null;

    }

}
