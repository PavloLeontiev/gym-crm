package com.paul.initializer;

import com.paul.initializer.impl.TraineeInitializer;
import com.paul.initializer.impl.TrainerInitializer;
import com.paul.initializer.impl.TrainingInitializer;
import com.paul.model.Trainee;
import com.paul.model.Trainer;
import com.paul.model.Training;

import java.util.Map;

public class StorageInitializationContext {

    private final Map<Class<?>, InMemoryStorageInitializer<Long, ?>> inMemoryInitializerStrategies;

    public StorageInitializationContext() {
        this.inMemoryInitializerStrategies = Map.of(
                Trainee.class, new TraineeInitializer(),
                Trainer.class, new TrainerInitializer(),
                Training.class, new TrainingInitializer()
        );
    }

    public InMemoryStorageInitializer<Long, ?> getStrategy(Class<?> type) {
        return inMemoryInitializerStrategies.get(type);
    }
}
