package com.paul.mapper;

import com.paul.dto.CreateTrainerDto;
import com.paul.model.Trainer;
import com.paul.model.TrainingType;
import com.paul.model.User;
import org.modelmapper.ModelMapper;

public class CreateTrainerMapper {

    private ModelMapper mapper = new ModelMapper();

    public Trainer toTrainerEntity(CreateTrainerDto dto) {
        TrainingType trainingType = TrainingType.fromName(dto.getSpecialization());
        return new Trainer(null, null, trainingType == null ? null : trainingType.getId());
    }

    public User toUserEntity(CreateTrainerDto dto) {
        return dto == null ? null : mapper.map(dto, User.class);
    }

    public CreateTrainerDto toTrainerDto(Trainer trainer, User user) {
        return CreateTrainerDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .specialization(TrainingType.fromId(trainer.getSpecializationId()).getTypeName())
                .build();
    }
}
