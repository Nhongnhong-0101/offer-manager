package com.example.offer_management_be.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName){
        super("Could not find user " + userName);
    }
}
