package com.gym_progress.controller;

import com.gym_progress.UsersService.UserService;
import com.gym_progress.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllCategories() {
        List<User> categories = userService.getAllUsers();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> getUserById (@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) throw new IllegalArgumentException("no se encontro el empleado con el id " + id + " .");
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
