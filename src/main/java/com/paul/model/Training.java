package com.paul.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    private Long id;
    private Long traineeId;
    private Long trainerId;
    private String trainingName;
    private Integer trainingTypeId;
    private LocalDate trainingDate;
    private Integer trainingDuration;
}
