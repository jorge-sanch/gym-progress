package com.gym_progress.model;

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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Column(nullable = true)
    private String name;

    @Column
    private String birthday;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol", nullable = false)
    private Rol rol;

}
