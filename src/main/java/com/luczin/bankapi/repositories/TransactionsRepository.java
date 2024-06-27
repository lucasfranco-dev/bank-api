package com.luczin.bankapi.repositories;

import com.luczin.bankapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transaction, String> {
}
