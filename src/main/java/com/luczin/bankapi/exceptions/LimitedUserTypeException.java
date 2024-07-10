package com.luczin.bankapi.exceptions;

public class LimitedUserTypeException extends RuntimeException{

    public LimitedUserTypeException(){
        super();
    }

    public LimitedUserTypeException(String message){
        super(message);
    }
}
