package com.paul.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeTrainer {

    private Long id;
    private Long traineeId;
    private Long trainerId;
}
