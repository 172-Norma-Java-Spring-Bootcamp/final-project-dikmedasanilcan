package com.example.finalproject.request.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditCardRequest {

    private long customer_id;
    private double creditLimit;

}