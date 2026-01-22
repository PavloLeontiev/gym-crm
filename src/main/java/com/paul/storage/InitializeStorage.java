package com.paul.storage;

import java.util.Map;

public interface InitializeStorage<T> {

    Class<T> getEntityClass();
    String getInitializeFilePath();
}
