package com.seyrek.vendingmachine.exceptions;

public class SellingNotSuccessfulException extends RuntimeException {
    public SellingNotSuccessfulException(String message) {
        super(message);
    }
}
