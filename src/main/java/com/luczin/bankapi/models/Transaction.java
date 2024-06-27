package com.luczin.bankapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = Transaction.TABLE_NAME)
@Table(name = Transaction.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Transaction {
    public final static String TABLE_NAME = "transactions";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User receiver;

    private BigDecimal amount;

    private Boolean reversed;
}
