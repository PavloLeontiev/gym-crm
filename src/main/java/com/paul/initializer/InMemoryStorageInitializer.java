package com.paul.initializer;

import com.paul.storage.Storage;

import java.util.List;

public interface InMemoryStorageInitializer <K extends Number, T> {

    void initialize(
            Storage<K> storage,
            String[] header,
            List<String[]> rows
    );
}
