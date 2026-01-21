package com.paul.storage;

import com.paul.model.Identifiable;

import java.util.List;
import java.util.Map;

public interface InitializeStorage<T extends Identifiable<ID>, ID> {

    Map<ID, T> getStorage();
    Class<T> getEntityClass();
    String getInitializeFilePath();
}
