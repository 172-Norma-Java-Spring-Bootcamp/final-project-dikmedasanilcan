package com.example.finalproject.repository.payment;

import com.example.finalproject.entity.account.DepositAccount;
import com.example.finalproject.entity.payment.AutoPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoPaymentRepository extends JpaRepository<AutoPayment,Long> {

    AutoPayment findByDepositAccount(DepositAccount depositAccount);

}
