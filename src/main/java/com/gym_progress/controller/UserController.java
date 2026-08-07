package com.gym_progress.controller;

import com.gym_progress.dto.LoginResponse;
import com.gym_progress.dto.RegisterRequest;
import com.gym_progress.dto.RegisterResponse;
import com.gym_progress.model.User;
import com.gym_progress.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Endpoint de registro
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            // Registrar usuario
            User newUser = userService.registerUser(registerRequest);

            // Crear respuesta
            RegisterResponse response = new RegisterResponse(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage(), "success", false)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", "Error al registrar usuario: " + e.getMessage(), "success", false)
            );
        }
    }

    // Endpoint de login (existente)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        try {
            // Obtener usuario
            User user = userService.findByEmail(loginRequest.getEmail());
            String token = userService.loginUser(loginRequest);

            // Crear respuesta
            LoginResponse response = new LoginResponse(token, user);

            // ✅ Asegurar que devuelve JSON
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", e.getMessage(), "success", false));
        }
    }
}