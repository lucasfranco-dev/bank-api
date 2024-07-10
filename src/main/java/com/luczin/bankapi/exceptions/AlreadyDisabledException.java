package com.luczin.bankapi.exceptions;

public class AlreadyDisabledException extends RuntimeException{

    public AlreadyDisabledException(){
        super();
    }

    public AlreadyDisabledException(String message){
        super(message);
    }
}
