package com.gym_progress.controller;

import com.gym_progress.UsersService.UserService;
import com.gym_progress.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllCategories() {
        List<Users> categories = userService.getAllUsers();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Users>> getUserById (@PathVariable Long id) {
        Optional<Users> user = userService.getUserById(id);
        if (user.isEmpty()) throw new IllegalArgumentException("no se encontro el empleado con el id " + id + " .");
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> saveUser(@RequestBody Users user){
        Users newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
