package com.paul.exception;

public class NoStorageInitializerFoundException extends RuntimeException {

    public NoStorageInitializerFoundException(Class<?> type) {
        super("No InMemoryStorageInitializer found for type: " + type.getName());
    }
}
