package com.luczin.bankapi.controllers;

import com.luczin.bankapi.dtos.TransactionDTO;
import com.luczin.bankapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity findNotRefundedTransactions(){
        return ResponseEntity.ok(transactionService.findAllNotRefunded());
    }

    @GetMapping("/all")
    public ResponseEntity findAllTransactions(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @PostMapping
    public ResponseEntity createTransaction(@RequestBody TransactionDTO data){
        transactionService.createTransaction(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transação realizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity refoundTransaction(@PathVariable String id){
        transactionService.reverseTransction(id);
        return ResponseEntity.noContent().build();
    }
}
