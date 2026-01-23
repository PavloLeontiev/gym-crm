package com.paul.initializer;

import com.paul.storage.Storage;

import java.util.List;

public interface InMemoryStorageInitializer <T> {

    Class<T> supports();

    void initialize(
            Storage storage,
            String[] header,
            List<String[]> rows
    );
}
