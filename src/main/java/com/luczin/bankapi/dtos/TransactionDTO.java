package com.luczin.bankapi.dtos;

import com.luczin.bankapi.models.User;

import java.math.BigDecimal;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal amount) {
}
