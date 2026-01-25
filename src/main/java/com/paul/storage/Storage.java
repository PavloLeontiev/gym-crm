package com.paul.storage;

import java.util.List;
import java.util.Optional;

public interface Storage<K extends Number> {

    K save(Object model);

    Object findById(K key);

    List<?> findAll();

    boolean exists(K key);

    void delete(K key);

    void clear();
}
