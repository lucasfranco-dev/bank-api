package com.luczin.bankapi.Exceptions;

public class LimitDaysRefundExceeded extends RuntimeException{

    public LimitDaysRefundExceeded(){
        super();
    }

    public LimitDaysRefundExceeded(String message){
        super(message);
    }
}
