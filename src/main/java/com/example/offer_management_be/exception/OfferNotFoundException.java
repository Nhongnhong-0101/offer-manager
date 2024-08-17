package com.example.offer_management_be.exception;

public class OfferNotFoundException extends  RuntimeException{
    public OfferNotFoundException(Long id){
        super("Could not find offer " + id);
    }
}
