package com.example.finalproject.repository;

import com.example.finalproject.entity.customer.Customer;
import com.example.finalproject.entity.operation.SystemOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SystemOperationsRepository extends JpaRepository<SystemOperations,Long> {

    List<SystemOperations> findByCustomer(Customer customer);
    SystemOperations findByOperationId(long id);
    SystemOperations findByOperationType(String type);

}

