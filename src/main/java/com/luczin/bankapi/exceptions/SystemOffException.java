package com.luczin.bankapi.exceptions;

public class SystemOffException extends RuntimeException{

    public SystemOffException(){
        super();
    }

    public SystemOffException(String message){
        super(message);
    }
}
