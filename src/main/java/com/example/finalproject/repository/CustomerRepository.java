package com.example.finalproject.repository;

import com.example.finalproject.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findById(long id);
    Customer findByContact_Email(String email);
    Customer findByContact_PhoneNumber(String phoneNumber);
    Customer findByName(String name);
    Customer findByIdentificationNumber(String idNumber);

}