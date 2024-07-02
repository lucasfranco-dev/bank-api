package com.luczin.bankapi.Exceptions;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException(){
        super();
    }

    public InsufficientFundsException(String message){
        super(message);
    }
}
