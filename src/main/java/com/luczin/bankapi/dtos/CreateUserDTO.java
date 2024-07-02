package com.luczin.bankapi.dtos;

import com.luczin.bankapi.models.UserType;

public record CreateUserDTO (String firstName, String lastName, String cpf, String email, String password, UserType userType) {
}
