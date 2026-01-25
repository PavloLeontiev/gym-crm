package com.paul.exception;

public class StorageDoesNotSupportUsernameIndexException extends IllegalArgumentException {

    public StorageDoesNotSupportUsernameIndexException(Class<?> storageClass) {
        super("Storage must implement UsernameIndexStorage, but " + storageClass.getName() + " does not.");
    }
}

