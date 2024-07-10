package com.luczin.bankapi.exceptions;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(){
        super();
    }

    public DuplicateUserException(String message){
        super(message);
    }
}
