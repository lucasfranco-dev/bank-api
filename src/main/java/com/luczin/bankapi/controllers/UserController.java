package com.luczin.bankapi.controllers;

import com.luczin.bankapi.dtos.CreateUserDTO;
import com.luczin.bankapi.dtos.UpdateUserDTO;
import com.luczin.bankapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity findAllUsers(){
        return ResponseEntity.ok().body(userService.findActiveUsers());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody CreateUserDTO data){
        userService.createUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado!");
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO data){
        userService.updateUser(data);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desactiveUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
