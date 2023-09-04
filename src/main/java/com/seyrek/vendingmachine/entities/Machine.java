package com.seyrek.vendingmachine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    @Id
    int id;
    int totalPrice;
    String coolingDate;

}
