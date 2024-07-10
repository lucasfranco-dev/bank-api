package com.luczin.bankapi.exceptions;

public class NullRequiredFieldsException extends RuntimeException{

    public NullRequiredFieldsException(){
        super();
    }

    public NullRequiredFieldsException(String message){
        super(message);
    }
}
