package com.paul.mapper;

import com.paul.dto.CreateTrainerDto;
import com.paul.dto.TrainerDto;
import com.paul.model.Trainer;
import com.paul.model.TrainingType;
import com.paul.model.User;

public class TrainerMapper {

    public Trainer toTrainerEntity(TrainerDto dto) {
        TrainingType trainingType = TrainingType.fromName(dto.getSpecialization());
        return new Trainer(dto.getId(), dto.getUserId(), trainingType == null ? null : trainingType.getId());
    }

    public User toUserEntity(TrainerDto dto) {
        return new User(dto.getUserId(), dto.getFirstName(), dto.getLastName(), dto.getUsername(), null, null);
    }

    public TrainerDto toTrainerDto(Trainer trainer, User user) {
        return TrainerDto.builder()
                .id(trainer.getId())
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .isActive(true)
                .specialization(TrainingType.fromId(trainer.getSpecializationId()).getTypeName())
                .build();
    }
}
