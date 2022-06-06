package com.example.finalproject.service;

import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.request.customer.create.CreateCustomerRequest;
import com.example.finalproject.request.customer.update.UpdateAddress;
import com.example.finalproject.request.customer.update.UpdateEmail;
import com.example.finalproject.request.customer.update.UpdatePhoneNumber;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    ResponseEntity<Object> create(CreateCustomerRequest request) throws Exception;
    List<Customer> findAll();
    ResponseEntity<Object> deleteCustomer(long id);
    Customer findByIdentificationNumber(String idNumber);
    ResponseEntity<Object> updateEmail(UpdateEmail email, long id);
    ResponseEntity<Object> updatePhoneNumber(UpdatePhoneNumber number, long id);
    ResponseEntity<Object> updateAddress(UpdateAddress address, long id);


}