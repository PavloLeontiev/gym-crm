package com.paul.initializer.impl;

import com.paul.model.Trainee;
import com.paul.model.User;
import com.paul.storage.Storage;

import java.time.LocalDate;
import java.util.List;

public class TraineeInitializer extends AbstractUserInitializer<Trainee> {

    @Override
    public void initialize(Storage<Long> storage, String[] header, List<String[]> rows) {
        for (String[] row : rows) {

            User user = new User();
            Trainee trainee = new Trainee();

            for (int i = 0; i < header.length; i++) {
                String column = header[i];
                String value = row[i];

                switch (column) {
                    case "firstName" -> user.setFirstName(value);
                    case "lastName" -> user.setLastName(value);
                    case "username" -> user.setUsername(value);
                    case "password" -> user.setPassword(value);
                    case "isActive" -> user.setIsActive(Boolean.parseBoolean(value));

                    case "dateOfBirth" -> trainee.setDateOfBirth(LocalDate.parse(value));
                    case "address" -> trainee.setAddress(value);
                }
            }

            setupUser(storage, user);
            Long id = storage.save(user);
            trainee.setUserId(id);
            storage.save(trainee);
        }
    }
}

