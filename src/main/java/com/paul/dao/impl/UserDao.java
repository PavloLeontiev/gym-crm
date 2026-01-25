package com.paul.dao.impl;

import com.paul.dao.Dao;
import com.paul.model.User;
import com.paul.storage.Storage;
import com.paul.storage.index.UsernameIndexStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao implements Dao<User, Long> {

    private Storage<Long> storage;

    @Autowired
    public void setStorage(Storage<Long> storage) {
        this.storage = storage;
    }

    @Override
    public User save(User model) {
        Long id = storage.save(model);
        model.setId(id);
        return model;
    }

    @Override
    public User findById(Long id) {
        boolean exists = storage.exists(id);
        if (exists) {
            return (User) storage.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return (List<User>) storage.findAll().stream()
                .filter(model -> model instanceof User)
                .toList();
    }

    @Override
    public void update(User model) {

        // TODO validation model for null
        Object maybeUser = storage.findById(model.getId());
        if (maybeUser instanceof User user) {
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
//            user.setPassword(model.getPassword());
            user.setIsActive(model.getIsActive());
        }
    }

    @Override
    public boolean delete(User model) {
        return false;
    }

    public User findByUsername(String username) {
        if (storage instanceof UsernameIndexStorage usernameIndexStorage) {
            Long idByUsername = (Long) usernameIndexStorage.findIdByUsername(username);
            if (idByUsername != null) {
                return (User) storage.findById(idByUsername);
            }
        } else {
            throw new UnsupportedOperationException("Not supported");
        }
        return null;
    }

    public Set<String> getAllUsername() {
        if (storage instanceof UsernameIndexStorage<?> usernameIndexStorage) {
            return (Set<String>) usernameIndexStorage.getUsernames();
        }
        return new HashSet<>();
    }
}
