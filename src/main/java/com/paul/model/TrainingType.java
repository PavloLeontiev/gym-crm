package com.paul.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
public enum TrainingType {
    FITNESS(1L, "Fitness"),
    YOGA(2L, "Yoga"),
    ZUMBA(3L, "Zumba"),
    STRENGTH(4L, "Strength"),
    RESISTANCE(5L, "Resistance");

    private final Long id;
    private final String typeName;
}
