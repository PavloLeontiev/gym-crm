package com.paul.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {

    private Long id;
    private Long userId;
    private LocalDate dateOfBirth;
    private String address;
}

