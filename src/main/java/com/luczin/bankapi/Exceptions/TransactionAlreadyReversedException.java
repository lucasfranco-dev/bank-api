package com.luczin.bankapi.Exceptions;

public class TransactionAlreadyReversedException extends RuntimeException{

    public TransactionAlreadyReversedException(){
        super();
    }

    public TransactionAlreadyReversedException(String message){
        super(message);
    }
}
