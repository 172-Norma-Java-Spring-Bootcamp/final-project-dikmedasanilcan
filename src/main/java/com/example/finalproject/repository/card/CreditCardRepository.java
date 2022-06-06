package com.example.finalproject.repository.card;

import com.example.finalproject.entity.card.CreditCard;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CardRepository {

    CreditCard findByCardNumber(String cardNumber);

    CreditCard findByExpiredDate(String expiredDate);


}
