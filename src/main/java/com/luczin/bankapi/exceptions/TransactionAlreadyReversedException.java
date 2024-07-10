package com.luczin.bankapi.exceptions;

public class TransactionAlreadyReversedException extends RuntimeException{

    public TransactionAlreadyReversedException(){
        super();
    }

    public TransactionAlreadyReversedException(String message){
        super(message);
    }
}
