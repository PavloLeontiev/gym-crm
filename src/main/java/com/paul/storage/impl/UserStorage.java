package com.paul.storage.impl;

import com.paul.model.User;
import com.paul.storage.GenericStorage;
import org.springframework.stereotype.Repository;

@Repository
public class UserStorage extends GenericStorage<Long, User> {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public String getInitializeFilePath() {
        return "";
    }
}
