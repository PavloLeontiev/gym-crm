package com.paul;

import com.paul.config.ApplicationConfiguration;

import com.paul.dto.CreateTrainerDto;
import com.paul.dto.TrainerDto;
import com.paul.mapper.TrainerMapper;
import com.paul.model.Trainer;
import com.paul.model.User;
import com.paul.service.TrainerService;
import com.paul.storage.impl.InMemoryStorage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        TrainerService bean = annotationConfigApplicationContext.getBean(TrainerService.class);

        CreateTrainerDto build = CreateTrainerDto.builder()
                .firstName("John")
                .lastName("Doe")
                .specialization("Yoga")
                .build();



        TrainerDto byId = bean.findById(4L);
        TrainerDto newTrainer = bean.create(build);
        Long idTrainer = newTrainer.getId();
        Long idUser = newTrainer.getUserId();
        List<TrainerDto> all = bean.findAll();

        TrainerDto updatedTrainer = TrainerDto.builder()
                .id(idTrainer)
                .userId(idUser)
                .firstName("John")
                .lastName("Leo")
                .username("John.Doe1")
                .isActive(false)
                .specialization("Fitness")
                .build();

        bean.update(updatedTrainer);
        TrainerDto updatedTrainerFind = bean.findById(idTrainer);

        System.out.println(byId);
        System.out.println(newTrainer);
        all.stream().forEach(System.out::println);
//        System.out.println(updatedTrainerFind);
//        System.out.println();
    }
}
