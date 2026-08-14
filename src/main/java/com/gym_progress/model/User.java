package com.gym_progress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Column(nullable = true)
    private String name;

    @Column
    private String lastName;

    @Column
    private String birthday;

    @Column
    private String password;

    @Column
    private String email;

    // ✅ Agregar la relación con Rol
    @ManyToOne
    @JoinColumn(name = "rol_id") // Este es el nombre de la columna en la tabla users
    private Rol rol;
}