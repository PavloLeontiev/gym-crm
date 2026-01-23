package com.paul.initializer;

import com.paul.model.Trainer;
import com.paul.model.User;
import com.paul.storage.Storage;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class TrainerInitializer implements InMemoryStorageInitializer<Trainer> {

    private final Class<User> userClass = User.class;
    private final Class<Trainer> trainerClass = Trainer.class;

    @Override
    public Class<Trainer> supports() {
        return Trainer.class;
    }

    @Override
    public void initialize(Storage storage, String[] header, List<String[]> rows) {
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

//            storage.save(userClass, user);
//
//            trainer.setUserId(user.getId());
//
//            storage.save(trainerClass, trainer);
        }
    }
}
