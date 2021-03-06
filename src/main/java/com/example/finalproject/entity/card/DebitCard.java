package com.example.finalproject.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.finalproject.entity.account.DepositAccount;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebitCard extends Card{



    @ManyToOne
    @JsonIgnore
    private DepositAccount depositAccount;



}
