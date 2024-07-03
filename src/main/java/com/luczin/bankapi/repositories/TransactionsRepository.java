package com.luczin.bankapi.repositories;

import com.luczin.bankapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, String> {

    public List<Transaction> findAllByRefundedFalse();
}
