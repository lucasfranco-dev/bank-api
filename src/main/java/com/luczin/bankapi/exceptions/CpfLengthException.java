package com.luczin.bankapi.exceptions;

public class CpfLengthException extends RuntimeException{

    public CpfLengthException(){
        super();
    }

    public CpfLengthException(String message){
        super(message);
    }
}
