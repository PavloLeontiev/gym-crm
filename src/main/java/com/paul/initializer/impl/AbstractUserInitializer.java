package com.paul.initializer.impl;

import com.paul.exception.StorageDoesNotSupportUsernameIndexException;
import com.paul.initializer.InMemoryStorageInitializer;
import com.paul.model.User;
import com.paul.storage.Storage;
import com.paul.storage.index.UsernameIndexStorage;
import com.paul.util.CredentialGenerator;

public abstract class AbstractUserInitializer<T> implements InMemoryStorageInitializer<Long, T> {

    protected void setupUser(Storage<Long> storage, User user) {
        if (storage instanceof UsernameIndexStorage usernameIndexStorage) {
            String username = CredentialGenerator.generateUsername(
                    user.getFirstName(), user.getLastName(), usernameIndexStorage.getUsernames()
            );
            String password = CredentialGenerator.generatePassword();
            user.setUsername(username);
            user.setPassword(password);
        } else {
            throw new StorageDoesNotSupportUsernameIndexException(storage.getClass());
        }
    }
}
