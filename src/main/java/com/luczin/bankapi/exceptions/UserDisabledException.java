package com.luczin.bankapi.exceptions;

public class UserDisabledException extends RuntimeException{

    public UserDisabledException(){
        super();
    }

    public UserDisabledException(String message){
        super(message);
    }
}
