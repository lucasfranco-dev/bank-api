package com.luczin.bankapi.infra;

import com.luczin.bankapi.Exceptions.*;
import com.luczin.bankapi.dtos.ExceptionHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice()
public class RequestsExceptionHandler {

    @ExceptionHandler(AlreadyDisabledException.class)
    public ResponseEntity threatAlreadyDisabledException(){
        var message = new ExceptionHandlerDTO(AlreadyDisabledException.class, "Esta entitidade já esta desabilitada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(CpfLengthException.class)
    public ResponseEntity threatCpfLengthException(){
        var message = new ExceptionHandlerDTO(CpfLengthException.class,"Tamanho do CPF inválido");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity threatDuplicateUserException(){
        var message = new ExceptionHandlerDTO(DuplicateUserException.class,"Informações únicas duplicadas");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity threatInsufficientFundsException(){
        var message = new ExceptionHandlerDTO(InsufficientFundsException.class,"Saldo insuficiente");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(LimitDaysRefundExceeded.class)
    public ResponseEntity threatLimitDaysRefundExceeded(){
        var message = new ExceptionHandlerDTO(LimitDaysRefundExceeded.class,"Limite de dias para estorno excedidos");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(LimitedUserTypeException.class)
    public ResponseEntity threatLimitedUserTypeException(){
        var message = new ExceptionHandlerDTO(LimitedUserTypeException.class,"Um dos usuários não tem permissão para efetuar essa transação");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(NullRequiredFieldsException.class)
    public ResponseEntity threatNullRequiredFieldsException(){
        var message = new ExceptionHandlerDTO(NullRequiredFieldsException.class,"Um dos campos obrigatórios não foi preenchido corretamente");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(SystemOffException.class)
    public ResponseEntity threatSystemOffException(){
        var message = new ExceptionHandlerDTO(SystemOffException.class,"Erro de conexão com o servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(TransactionAlreadyReversedException.class)
    public ResponseEntity threatTransactionAlreadyReversedException(){
        var message = new ExceptionHandlerDTO(TransactionAlreadyReversedException.class,"Esta transação ja foi estornada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity threatUserDisabledException(){
        var message = new ExceptionHandlerDTO(UserDisabledException.class,"Usuários desabilitados não podem participar de transações");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity threatHttpClientErrorException(){
        var message = new ExceptionHandlerDTO(HttpClientErrorException.class, "Erro de conexão com o servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
