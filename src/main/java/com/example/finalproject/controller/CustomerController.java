package com.example.finalproject.controller;


import javassist.NotFoundException;
import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.operation.SystemOperations;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.SystemOperationsRepository;
import com.example.finalproject.request.customer.create.CreateCustomerRequest;
import com.example.finalproject.request.customer.update.UpdateAddress;
import com.example.finalproject.request.customer.update.UpdateEmail;
import com.example.finalproject.request.customer.update.UpdatePhoneNumber;
import com.example.finalproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SystemOperationsRepository systemOperationsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CreateCustomerRequest request) throws Exception {
        return customerService.create(request);
    }

    @GetMapping("/listAll")
    public List<Customer> listAll(){
        return customerService.findAll();
    }

    @DeleteMapping("/delete/ById/{id}")
    private ResponseEntity<Object> deleteById(@PathVariable long id){
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/update/email/{id}")
    private ResponseEntity<Object> updateEmail(@PathVariable long id,@RequestBody UpdateEmail email){
        return customerService.updateEmail(email,id);
    }

    @PutMapping("/update/phoneNumber/{id}")
    private ResponseEntity<Object> updatePhoneNumber(@PathVariable long id,@RequestBody UpdatePhoneNumber number){
        return customerService.updatePhoneNumber(number,id);
    }
    @PutMapping("/update/address/{id}")
    private ResponseEntity<Object> updateAddress(@RequestBody UpdateAddress address, @PathVariable long id){
        return customerService.updateAddress(address,id);
    }

    @GetMapping("/list/customerOperation/{customer_id}")
    private List<SystemOperations> getCustomerOperations(@PathVariable long customer_id) throws NotFoundException {

        Customer customer = customerRepository.findById(customer_id);
        if(customer==null)
            throw new NotFoundException("Customer not found");

        return systemOperationsRepository.findByCustomer(customer);
    }


}
