package com.example.finalproject.request.transaction;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtOnCardRequest {

    private String cardNumber;
    private String creditCardNumber;
    private double debt;

}

