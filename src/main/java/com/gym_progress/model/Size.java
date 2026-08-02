package com.gym_progress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "size")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String weight;

    @Column
    private String date;

    @Column
    private String notes;

    @Column
    private String goal;

    @ManyToOne
    @JoinColumn(name = "users", nullable = false)
    private User user;

}
