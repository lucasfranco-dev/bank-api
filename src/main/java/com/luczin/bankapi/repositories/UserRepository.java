package com.luczin.bankapi.repositories;

import com.luczin.bankapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findUserById(Long id);
    public List<User> findAllByActiveTrue();

}
