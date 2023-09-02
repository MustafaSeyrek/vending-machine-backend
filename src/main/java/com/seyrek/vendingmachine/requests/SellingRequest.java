package com.seyrek.vendingmachine.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellingRequest {
    private int machineId;
    private int productId;
    private int paidPrice;
}
