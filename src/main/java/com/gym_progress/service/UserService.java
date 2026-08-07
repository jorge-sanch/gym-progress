package com.gym_progress.service;

import com.gym_progress.dto.RegisterRequest;
import com.gym_progress.model.User;
import com.gym_progress.model.Rol;
import com.gym_progress.repository.UserRepository;
import com.gym_progress.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RolService rolService,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
        this.jwtService = jwtService;
    }

//    ?Metodo para llevar el usuario a los otros servicios
    public Optional<User> findByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    // ✅ Métod para registrar usuario
    public User registerUser(RegisterRequest registerRequest) {
        // 1. Verificar si el email ya existe
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 2. Crear nuevo usuario
        User user = new User();
        user.setName(registerRequest.getName());
        user.setLastName(registerRequest.getLastName());
        user.setBirthday(registerRequest.getBirthday());
        user.setEmail(registerRequest.getEmail());

        // 3. Encriptar contraseña
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // 4. Asignar rol por defecto (ID 2)
        Rol defaultRol = rolService.getDefaultRol();
        user.setRol(defaultRol);

        // 5. Guardar en la base de datos
        return userRepository.save(user);
    }

    // Métod para login (existente)
    public String loginUser(User user) {
        User savedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales Incorrectas"));

        if (passwordEncoder.matches(user.getPassword(), savedUser.getPassword())) {
            return jwtService.generateToken(savedUser);
        } else {
            throw new IllegalArgumentException("Credenciales Incorrectas");
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}