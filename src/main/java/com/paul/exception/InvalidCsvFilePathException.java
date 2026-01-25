package com.paul.exception;

public class InvalidCsvFilePathException extends IllegalArgumentException {
    public InvalidCsvFilePathException(String filePath) {
        super("CSV file path must start with 'classpath:data/' and end with '.csv'. Provided: " + filePath);
    }
}