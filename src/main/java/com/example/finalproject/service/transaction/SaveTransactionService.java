package com.example.finalproject.service.transaction;

import com.example.finalproject.domain.CurrencyOperation;
import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.account.Account;
import com.example.finalproject.request.transaction.AccountTransactionRequest;
import com.example.finalproject.request.transaction.CardTransactionRequest;
import com.example.finalproject.request.transaction.DebtOnAccountRequest;
import com.example.finalproject.request.transaction.TransferTransactionRequest;

public interface SaveTransactionService {

    public void saveBalanceOnAccount(String txnType, Account account, AccountTransactionRequest request);
    public void saveDebtOnAccount(Account account, DebtOnAccountRequest request, double txnAmount);
    public void saveCreditCardTransaction(CardTransactionRequest request, Customer customer, String txnType);
    public void saveDebitCardTransaction(CardTransactionRequest request, Account account, String txnType,double txnAmount);
    public void saveTransferTransaction(TransferTransactionRequest request, Account sender, Account receiver, String txnType, CurrencyOperation currencyClass);
    public void saveTransferTransactionBetweenAccounts(Account account,double amount,String txnType);
    public void saveAutoPaymentTransaction(Account account,double amount,String txnType);

}
