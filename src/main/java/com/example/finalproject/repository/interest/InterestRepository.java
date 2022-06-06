package com.example.finalproject.repository.interest;

import com.example.finalproject.entity.interest.DailyInterest;
import com.example.finalproject.entity.interest.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface InterestRepository extends JpaRepository<Interest,Long> {

    DailyInterest findByCurrencyType(String currencyType);

}
