package com.paul.initializer;

import com.paul.model.Trainee;
import com.paul.model.Training;
import com.paul.model.User;
import com.paul.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class TrainingInitializer implements InMemoryStorageInitializer<Training> {

    private final Class<Training> trainingClass = Training.class;

    @Override
    public Class<Training> supports() {
        return Training.class;
    }

    @Override
    public void initialize(Storage storage, String[] header, List<String[]> rows) {

        for (String[] row : rows) {

            Training training = new Training();

            for (int i = 0; i < header.length; i++) {
                String column = header[i];
                String value = row[i];

                switch (column) {
                    case "traineeId" -> training.setTraineeId(Long.valueOf(value));
                    case "trainerId" -> training.setTrainerId(Long.valueOf(value));
                    case "trainingName" -> training.setTrainingName(value);
                    case "trainingTypeId" -> training.setTrainingTypeId(Integer.valueOf(value));
                    case "trainingDate" ->
                            training.setTrainingDate(LocalDate.parse(value, DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("yyyy-MM-dd"))));
                    case "trainingDuration" -> training.setTrainingDuration(Integer.valueOf(value));

                }
            }

//            storage.save(trainingClass, training);
        }

    }
}
