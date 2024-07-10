package com.luczin.bankapi.services;

import com.luczin.bankapi.exceptions.*;
import com.luczin.bankapi.dtos.TransactionDTO;
import com.luczin.bankapi.infra.Utils;
import com.luczin.bankapi.models.Transaction;
import com.luczin.bankapi.models.User;
import com.luczin.bankapi.models.UserType;
import com.luczin.bankapi.repositories.TransactionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private AuthorizationService authService;

    @Autowired
    UserService userService;

    @Transactional
    public void createTransaction(TransactionDTO data){
        Transaction newTrans = new Transaction();
        newTrans.setReceiver(userService.getReferenceById(data.receiverId()));
        newTrans.setSender(userService.getReferenceById(data.senderId()));
        newTrans.setAmount(data.amount());
        validateTransaction(newTrans);

        User sender = newTrans.getSender();
        User receiver = newTrans.getReceiver();
        BigDecimal amount = newTrans.getAmount();

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));


        transactionsRepository.save(newTrans);
    }

    public List<Transaction> findAllNotRefunded(){
        return transactionsRepository.findAllByRefundedFalse();
    }

    public List<Transaction> findAll(){
        return transactionsRepository.findAll();
    }

    @Transactional
    public void reverseTransction(String id){
        authService.systemCheck();

        Transaction transaction = transactionsRepository.getReferenceById(id);
        User sender = transaction.getSender();
        User receiver = transaction.getReceiver();
        BigDecimal amount = transaction.getAmount();
        Duration duration = Duration.between(transaction.getTimeStamp(), LocalDateTime.now());

        if (transaction.getRefunded()){
            throw new TransactionAlreadyReversedException("This transaction has already been reversed");
        }
        else if (duration.toDays() >= Transaction.LIMIT_DAYS_FOR_REFUND) {
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

        if (Utils.isNull(sender, receiver, amount)){
            throw new NullRequiredFieldsException("One of the required fields is empty or null");
        }

        //verificação de tipo de usuário
        if (sender.getUserType().equals(UserType.COMMON)){
            throw new LimitedUserTypeException("MERCHANT type users cannot send transactions");
        }

        //verificação de saldo
        else if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("The senderId does not have enough money for this transaction");
        }

        //verificação de usuário desativado
        else if (!sender.getActive() || !receiver.getActive()) {
            throw new UserDisabledException("One of the users involved in the transaction has their account deactivated");
        }

        //verificação de serviço autorizador
        authService.systemCheck();
    }
}
