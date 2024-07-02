package com.luczin.bankapi.Exceptions;

public class LimitedUserTypeException extends RuntimeException{

    public LimitedUserTypeException(){
        super();
    }

    public LimitedUserTypeException(String message){
        super(message);
    }
}
