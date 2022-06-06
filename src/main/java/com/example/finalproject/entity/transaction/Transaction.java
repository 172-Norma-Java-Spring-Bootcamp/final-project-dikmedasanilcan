package com.example.finalproject.entity.transaction;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.finalproject.entity.customer.Customer;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long transactionId;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
