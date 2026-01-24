package com.paul.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee implements Identifiable<Long> {

    private Long id;
    private Long userId;
    private LocalDate dateOfBirth;
    private String address;
}

