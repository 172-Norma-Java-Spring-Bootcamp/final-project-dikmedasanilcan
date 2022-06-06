package com.example.finalproject.repository.card;

import com.example.finalproject.entity.card.DebitCard;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardRepository extends CardRepository {

    DebitCard findByCardNumber(String cardNumber);
}
