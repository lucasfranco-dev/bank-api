package com.luczin.bankapi.services;

import com.luczin.bankapi.Exceptions.*;
import com.luczin.bankapi.dtos.TransactionDTO;
import com.luczin.bankapi.models.Transaction;
import com.luczin.bankapi.models.User;
import com.luczin.bankapi.repositories.TransactionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public void createTransaction(TransactionDTO data){
        Transaction newTrans = new Transaction(data);
        validateTransaction(newTrans);
        transactionsRepository.save(newTrans);
    }

    @Transactional
    public void reverseTransction(String id){
        systemCheck();
        Transaction transaction = transactionsRepository.getReferenceById(id);
        User sender = transaction.getSender();
        User receiver = transaction.getReceiver();
        BigDecimal amount = transaction.getAmount();
        Duration duration = Duration.between(transaction.getTimeStamp(), LocalDateTime.now());

        if (transaction.getRefunded()){
            throw new TransactionAlreadyReversedException("This transaction has already been reversed");
        }
        else if (duration.toDays() > Transaction.LIMIT_DAYS_FOR_REFUND) {
            throw new LimitDaysRefundExceeded("The day limit for refund has been exceeded");
        }

        //estornando
        sender.setBalance(sender.getBalance().add(amount));
        receiver.setBalance(receiver.getBalance().subtract(amount));
        transaction.setRefunded(true);
    }

    private void validateTransaction(Transaction transaction){
        User sender = transaction.getSender();
        User receiver = transaction.getReceiver();
        BigDecimal amount = transaction.getAmount();

        //verificação de tipo de usuário
        if (!sender.getUserType().equals("COMMON")){
            throw new LimitedUserTypeException("MERCHANT type users cannot send transactions");
        }

        //verificação de saldo
        else if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("The sender does not have enough money for this transaction");
        }

        //verificação de serviço autorizador
        systemCheck();
    }

    private Boolean systemCheck(){
        String url = "https://util.devi.tools/api/v2/authorize";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        if (responseBody.equals(null) || responseBody.contains("\"authorization\" : false")){
            throw new SystemOffException("The system mock is down!");
        }

        return true;
    }
}
