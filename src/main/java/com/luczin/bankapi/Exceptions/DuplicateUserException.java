package com.luczin.bankapi.Exceptions;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(){
        super();
    }

    public DuplicateUserException(String message){
        super(message);
    }
}
