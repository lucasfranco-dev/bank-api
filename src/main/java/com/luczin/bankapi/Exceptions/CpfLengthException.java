package com.luczin.bankapi.Exceptions;

public class CpfLengthException extends RuntimeException{

    public CpfLengthException(){
        super();
    }

    public CpfLengthException(String message){
        super(message);
    }
}
