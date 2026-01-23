package com.paul.initializer;

import com.paul.model.Trainer;

import java.util.List;
import java.util.Map;


public class StorageInitializationContext {

    private final Map<Class<?>, InMemoryStorageInitializer<?>> strategies;

    public StorageInitializationContext() {

        this.strategies = Map.of(
                Trainer.class, new TrainerInitializer()
        );
    }

    @SuppressWarnings("unchecked")
    public <T> InMemoryStorageInitializer<T> getStrategy(Class<T> type) {
        return (InMemoryStorageInitializer<T>) strategies.get(type);
    }
}
