package com.luczin.bankapi.Exceptions;

public class SystemOffException extends RuntimeException{

    public SystemOffException(){
        super();
    }

    public SystemOffException(String message){
        super(message);
    }
}
