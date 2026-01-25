package com.paul.initializer.impl;

import com.paul.model.Trainer;
import com.paul.model.User;
import com.paul.storage.Storage;

import java.util.List;

public class TrainerInitializer extends AbstractUserInitializer<Trainer> {

    @Override
    public void initialize(Storage<Long> storage, String[] header, List<String[]> rows) {
        for (String[] row : rows) {

            User user = new User();
            Trainer trainer = new Trainer();

            for (int i = 0; i < header.length; i++) {
                String column = header[i];
                String value = row[i];

                switch (column) {
                    case "firstName" -> user.setFirstName(value);
                    case "lastName"  -> user.setLastName(value);
                    case "username"  -> user.setUsername(value);
                    case "password"  -> user.setPassword(value);
                    case "isActive"  -> user.setIsActive(Boolean.parseBoolean(value));

                    case "specializationId" -> trainer.setSpecializationId(Integer.parseInt(value));
                }
            }

            setupUser(storage, user);
            Long id = storage.save(user);
            trainer.setUserId(id);
            storage.save(trainer);
        }
    }
}
