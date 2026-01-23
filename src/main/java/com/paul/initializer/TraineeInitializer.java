package com.paul.initializer;

import com.paul.model.Trainee;
import com.paul.model.Trainer;
import com.paul.model.User;
import com.paul.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class TraineeInitializer implements InMemoryStorageInitializer<Trainee> {

    private final Class<User> userClass = User.class;
    private final Class<Trainee> traineeClass = Trainee.class;

    @Override
    public Class<Trainee> supports() {
        return Trainee.class;
    }

    @Override
    public void initialize(Storage storage, String[] header, List<String[]> rows) {
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

                    case "dateOfBirth" ->
                            trainee.setDateOfBirth(LocalDate.parse(value, DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("yyyy-MM-dd"))));
                    case "address" -> trainee.setAddress(value);
                }
            }

            storage.save(userClass, user);

            trainee.setUserId(user.getId());

            storage.save(traineeClass, trainee);
        }
    }
}

