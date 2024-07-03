package com.luczin.bankapi.Exceptions;

public class NullRequiredFieldsException extends RuntimeException{

    public NullRequiredFieldsException(){
        super();
    }

    public NullRequiredFieldsException(String message){
        super(message);
    }
}
