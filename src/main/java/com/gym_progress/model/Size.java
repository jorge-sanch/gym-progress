package com.gym_progress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    public Long id;

    @Column
    public String weight;

    @Column
    public String date;

    @Column
    public String notes;
}
