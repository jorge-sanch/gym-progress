package com.gym_progress.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthday;
}
