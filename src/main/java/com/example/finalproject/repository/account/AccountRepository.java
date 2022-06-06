package com.example.finalproject.repository.account;

import com.example.finalproject.entity.account.Account;
import com.example.finalproject.entity.account.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.*;

@NoRepositoryBean
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByAccountNumber(String accountNumber);
    Account findByIbanNo(String ibanNo);

    List<Account> findByAccountType(String accountType);


}
