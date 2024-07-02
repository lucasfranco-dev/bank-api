package com.luczin.bankapi.models;

import com.luczin.bankapi.dtos.CreateUserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = User.TABLE_NAME)
@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    public final static String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated
    private UserType userType;

    private Boolean active;

    public User(CreateUserDTO createUserDTO){
        this.firstName = createUserDTO.firstName();
        this.lastName = createUserDTO.lastName();
        this.cpf = createUserDTO.cpf();
        this.email = createUserDTO.email();
        this.password = createUserDTO.password();
        this.userType = createUserDTO.userType();
    }
}
