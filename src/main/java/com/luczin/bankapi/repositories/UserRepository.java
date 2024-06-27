package com.luczin.bankapi.repositories;

import com.luczin.bankapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
