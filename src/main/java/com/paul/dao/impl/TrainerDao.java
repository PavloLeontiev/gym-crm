package com.paul.dao.impl;

import com.paul.dao.Dao;
import com.paul.model.Trainer;
import com.paul.model.User;
import com.paul.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainerDao implements Dao<Trainer, Long> {

    private Storage<Long> storage;

    @Autowired
    public void setStorage(Storage<Long> storage) {
        this.storage = storage;
    }


    @Override
    public Trainer save(Trainer model) {
        Long id = storage.save(model);
        model.setId(id);
        return model;
    }

    @Override
    public Trainer findById(Long id) {
        if (storage.exists(id)) {
            Object model = storage.findById(id);
            if (model instanceof Trainer trainer) {
                return trainer;
            }
        }
        return null;
    }

    @Override
    public List<Trainer> findAll() {
        return (List<Trainer>) storage.findAll().stream()
                .filter(model -> model instanceof Trainer)
                .toList();
    }

    @Override
    public boolean delete(Trainer model) {
        throw new UnsupportedOperationException("Delete operation not supported.");
    }

    @Override
    public void update(Trainer model) {
        Object maybeTrainer = storage.findById(model.getId());

        // TODO Validation model for null
        if (maybeTrainer instanceof Trainer trainer) {
//            trainer.setUserId(model.getUserId());
            trainer.setSpecializationId(model.getSpecializationId());
        }
    }
}
