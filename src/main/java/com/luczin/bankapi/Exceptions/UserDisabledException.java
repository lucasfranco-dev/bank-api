package com.luczin.bankapi.Exceptions;

public class UserDisabledException extends RuntimeException{

    public UserDisabledException(){
        super();
    }

    public UserDisabledException(String message){
        super(message);
    }
}
