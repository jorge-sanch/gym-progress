package com.gym_progress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.boot.query.BootQueryLogging;

@Entity
@Table(name = "exercise")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exercise {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bodyPart;

    @Column
    private String exerciseName;

    @Column
    private Float weight;

    @Column
    private Integer repetitions;
}
