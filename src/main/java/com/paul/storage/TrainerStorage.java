package com.paul.storage;

import com.paul.bpp.InitializeStorage;
import com.paul.model.Trainer;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainerStorage {

    @InitializeStorage("storage.trainers.file")
    private Map<Long, Trainer> trainers;

}
