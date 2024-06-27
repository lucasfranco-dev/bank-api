package com.luczin.bankapi.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = Transaction.TABLE_NAME)
@Table(name = Transaction.TABLE_NAME)
public class Transaction {
    public final static String TABLE_NAME = "transactions";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private User sender;

    private User receiver;

    private BigDecimal amount;
}
