package com.luczin.bankapi.dtos;

import com.luczin.bankapi.models.UserType;

public record UpdateUserDTO(Long id, String email, String password) {
}
