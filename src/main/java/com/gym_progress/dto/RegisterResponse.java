package com.gym_progress.dto;

import com.gym_progress.model.Rol;
import com.gym_progress.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String message;
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Rol rol;
    private boolean success;

    public RegisterResponse(User user) {
        this.message = "Usuario registrado exitosamente";
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.rol = user.getRol();
        this.success = true;
    }
}