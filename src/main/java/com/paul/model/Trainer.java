package com.paul.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isActive;
    private Integer specializationId;
}

