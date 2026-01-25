package com.paul.model;

import lombok.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum TrainingType {
    FITNESS(1, "Fitness"),
    YOGA(2, "Yoga"),
    ZUMBA(3, "Zumba"),
    STRENGTH(4, "Strength"),
    RESISTANCE(5, "Resistance");

    private final Integer id;
    private final String typeName;

    private static final Map<Integer, TrainingType> BY_ID =
            Arrays.stream(values()).collect(Collectors.toMap(TrainingType::getId, t -> t));

    public static TrainingType fromId(Integer id) {
        return id == null ? null : BY_ID.get(id);
    }

    public static TrainingType fromName(String name) {
        return BY_ID.values().stream()
                .filter(v -> v.getTypeName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
