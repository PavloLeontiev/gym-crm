package com.paul.model;

import lombok.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static final Map<Long, TrainingType> BY_ID =
            Arrays.stream(values()).collect(Collectors.toMap(TrainingType::getId, t -> t));

    public static TrainingType fromId(Long id) {
        return id == null ? null : BY_ID.get(id);
    }
}
