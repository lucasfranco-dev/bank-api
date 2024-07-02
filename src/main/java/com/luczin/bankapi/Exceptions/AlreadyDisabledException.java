package com.luczin.bankapi.Exceptions;

public class AlreadyDisabledException extends RuntimeException{

    public AlreadyDisabledException(){
        super();
    }

    public AlreadyDisabledException(String message){
        super(message);
    }
}
