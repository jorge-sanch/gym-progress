package com.gym_progress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rols")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = true, unique = true)
    private String name;

    @JsonIgnore
    @Column
    private String description;

    // ✅ Cambiar a mappedBy para relación bidireccional
    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<User> users;
}