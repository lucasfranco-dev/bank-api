package com.luczin.bankapi.services;

import com.luczin.bankapi.exceptions.AlreadyDisabledException;
import com.luczin.bankapi.exceptions.CpfLengthException;
import com.luczin.bankapi.exceptions.DuplicateUserException;
import com.luczin.bankapi.exceptions.NullRequiredFieldsException;
import com.luczin.bankapi.dtos.CreateUserDTO;
import com.luczin.bankapi.dtos.UpdateUserDTO;
import com.luczin.bankapi.infra.Utils;
import com.luczin.bankapi.models.User;
import com.luczin.bankapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findActiveUsers(){
        return userRepository.findAllByActiveTrue();
    }

    @Transactional
    public void createUser(CreateUserDTO data){
        User newUser = new User(data);
        validateUser(newUser);
        userRepository.save(newUser);
    }


    @Transactional
    public void updateUser(UpdateUserDTO data){
        User user = userRepository.getReferenceById(data.id());

        user.setEmail(data.email());
        user.setPassword(data.password());
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.getReferenceById(id);

        if (!user.getActive()){
            throw new AlreadyDisabledException("This user is already deactivated");
        }
        else {
            user.setBalance(BigDecimal.valueOf(0));
            user.setActive(false);
        }
    }

    public User getReferenceById(Long id){
        return userRepository.getReferenceById(id);
    }

    public void validateUser(User user){
        List<User> allUsers = userRepository.findAll();

        if (Utils.isNull(user.getFirstName(), user.getLastName(), user.getCpf(), user.getEmail(), user.getUserType(),
                user.getPassword())){
            throw new NullRequiredFieldsException("One of the required fields is empty or null");
        }

        for (int i = 0; i < allUsers.size(); i++) {
            if (user.getCpf().equals(allUsers.get(i).getCpf()) || user.getEmail().equals(allUsers.get(i).getEmail())){
                throw new DuplicateUserException("Are any of the unique parameters the same as those of an existing user");
            }
        }

        if (user.getCpf().length() != 11){
            throw new CpfLengthException("Every CPF must have exactly 11 digits");
        }
    }
}
