package com.luczin.bankapi.exceptions;

public class LimitDaysRefundExceeded extends RuntimeException{

    public LimitDaysRefundExceeded(){
        super();
    }

    public LimitDaysRefundExceeded(String message){
        super(message);
    }
}
