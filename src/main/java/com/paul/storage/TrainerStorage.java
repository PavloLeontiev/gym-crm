package com.paul.storage;

import com.paul.model.Trainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainerStorage implements InitializeStorage<Trainer, Long> {

    @Value("${storage.trainers.file}")
    private String keyProperty;

    private Map<Long, Trainer> storage;

    @Override
    public String getInitializeFilePath() {
        return keyProperty;
    }

    @Override
    public Map<Long, Trainer> getStorage() {
        return storage;
    }

    @Override
    public Class<Trainer> getEntityClass() {
        return Trainer.class;
    }
}
