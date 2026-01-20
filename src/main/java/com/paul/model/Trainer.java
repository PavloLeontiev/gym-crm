package com.paul.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Trainer extends User{

    public Trainer(Long id, String firstName, String lastName, String username,
                   String password, Boolean isActive, Integer specializationId) {
        super(id, firstName, lastName, username, password, isActive);
        this.specializationId = specializationId;
    }

    private Integer specializationId;
}

