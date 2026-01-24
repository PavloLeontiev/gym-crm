package com.paul.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer implements Identifiable<Long> {

    private Long id;
    private Long userId;
    private Integer specializationId;
}

