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
public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private UserRolResponse rol;

    // Constructor que recibe token y User
    public LoginResponse(String token, User user) {
        this.token = token;
        this.type = "Bearer";
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.lastName = user.getLastName();

        // ✅ Evitar recursión infinita también aquí
        if (user.getRol() != null) {
            this.rol = new UserRolResponse(user.getRol());
        }
    }
}

// Clase auxiliar para el rol (sin lista de usuarios)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UserRolResponse {
    private Long id;
    private String name;
    private String description;

    public UserRolResponse(Rol rol) {
        this.id = rol.getId();
        this.name = rol.getName();
        this.description = rol.getDescription();
    }
}