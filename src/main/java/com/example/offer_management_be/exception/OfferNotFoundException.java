package com.example.offer_management_be.exception;

import java.util.UUID;

public class OfferNotFoundException extends  RuntimeException{
    public OfferNotFoundException(UUID id){
        super("Could not find offer " + id);
    }
}
