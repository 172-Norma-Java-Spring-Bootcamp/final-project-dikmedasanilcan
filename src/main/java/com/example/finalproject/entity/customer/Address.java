package com.example.finalproject.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;
    private String city;
    private String zipcode;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Customer customer;

}
