package com.example.finalproject.repository.credit;

import com.example.finalproject.entity.credit.CreditPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditPointRepository extends JpaRepository<CreditPoint, Long> {

    CreditPoint findByRanking(int ranking);
}
