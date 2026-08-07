package com.gym_progress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Table(name = "diet")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diet {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime foodTime;

    @Column
    private String food;

    @Column
    private Double water;

    @Column
    private DayOfWeek day;

    @Column
    private String notes;

   @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
