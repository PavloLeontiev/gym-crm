package com.paul.exception;

public class ModelClassNotFoundException extends IllegalArgumentException {
    public ModelClassNotFoundException(String className) {
        super("Model class not found in package: " + className);
    }
}

