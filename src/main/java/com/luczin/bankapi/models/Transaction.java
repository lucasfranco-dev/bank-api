package com.luczin.bankapi.models;

import com.luczin.bankapi.dtos.TransactionDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = Transaction.TABLE_NAME)
@Table(name = Transaction.TABLE_NAME)
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Transaction {
    public final static String TABLE_NAME = "transactions";
    public final static Integer LIMIT_DAYS_FOR_REFUND = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private BigDecimal amount;

    private final LocalDateTime timeStamp;

    private Boolean refunded;

    public Transaction(){
        this.refunded = false;
        this.timeStamp = LocalDateTime.now();
    }
}
