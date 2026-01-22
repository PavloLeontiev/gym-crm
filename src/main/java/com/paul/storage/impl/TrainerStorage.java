package com.paul.storage.impl;

import com.paul.model.Trainer;
import com.paul.storage.GenericStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerStorage extends GenericStorage<Long, Trainer> {

    @Value("${storage.trainers.file}")
    private String keyProperty;

    @Override
    public Class<Trainer> getEntityClass() {
        return Trainer.class;
    }

    @Override
    public String getInitializeFilePath() {
        return keyProperty;
    }
}
