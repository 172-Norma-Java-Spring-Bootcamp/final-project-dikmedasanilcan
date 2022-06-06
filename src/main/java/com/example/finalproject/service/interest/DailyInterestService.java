package com.example.finalproject.service.interest;

import org.springframework.http.ResponseEntity;

public interface DailyInterestService {

    public ResponseEntity<Object> calculateInterest(String currencyType, double starterBalance, double dayNumber);
}
