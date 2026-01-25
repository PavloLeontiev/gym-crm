package com.paul.service;

import com.paul.dao.impl.TrainerDao;
import com.paul.dao.impl.UserDao;
import com.paul.dto.CreateTrainerDto;
import com.paul.dto.TrainerDto;
import com.paul.mapper.CreateTrainerMapper;
import com.paul.mapper.TrainerMapper;
import com.paul.model.Trainer;
import com.paul.model.User;
import com.paul.util.CredentialGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {

    private TrainerDao trainerDao;
    private UserDao userDao;
    private TrainerMapper trainerMapper = new TrainerMapper();
    private CreateTrainerMapper createTrainerMapper = new CreateTrainerMapper();

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao, UserDao userDao) {
        this.trainerDao = trainerDao;
        this.userDao = userDao;
    }

    public TrainerDto create(CreateTrainerDto trainerDtoDto) {
        //  TODO validation

        User user = createTrainerMapper.toUserEntity(trainerDtoDto);
        String username = CredentialGenerator.generateUsername
                (user.getFirstName(), user.getLastName(), userDao.getAllUsername());
        String password = CredentialGenerator.generatePassword();
        user.setUsername(username);
        user.setPassword(password);
        User savedUser = userDao.save(user);

        Trainer trainer = createTrainerMapper.toTrainerEntity(trainerDtoDto);
        trainer.setUserId(savedUser.getId());
        Trainer savedTrainer = trainerDao.save(trainer);

        return trainerMapper.toTrainerDto(savedTrainer, savedUser);
    }

    public List<TrainerDto> findAll() {
        List<Trainer> trainers = trainerDao.findAll();
        List<User> users = userDao.findAll();
        List<TrainerDto> trainerDtos = new ArrayList<>();

        for (Trainer trainer : trainers) {
            Long userId = trainer.getUserId();
            for (User user : users) {
                if (userId.equals(user.getId())) {
                    TrainerDto trainerDto = trainerMapper.toTrainerDto(trainer, user);
                    trainerDtos.add(trainerDto);
                }
            }
        }
        return trainerDtos;
    }

    public TrainerDto findById(Long id) {
        TrainerDto trainerDto = null;
        Trainer trainer = trainerDao.findById(id);

        if (trainer != null) {
            User user = userDao.findById(trainer.getUserId());
            trainerDto = trainerMapper.toTrainerDto(trainer, user);
        }

        return trainerDto;
    }

    public void update(TrainerDto trainerDto) {
        User user = trainerMapper.toUserEntity(trainerDto);
        Trainer trainer = trainerMapper.toTrainerEntity(trainerDto);

        userDao.update(user);
        trainerDao.update(trainer);
    }
}
