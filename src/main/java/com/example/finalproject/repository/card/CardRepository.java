package com.example.finalproject.repository.card;

import com.example.finalproject.entity.card.Card;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface CardRepository extends JpaRepository<Card,Long> {
}
