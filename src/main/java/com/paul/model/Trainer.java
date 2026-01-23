package com.paul.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    private Long id;
    private Long userId;
    private Integer specializationId;
}

