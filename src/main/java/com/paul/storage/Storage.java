package com.paul.storage;

import com.paul.model.Identifiable;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface Storage<K extends Number> {

    K save(Object model);

    Optional<?> findByKey(K key);

    Collection<?> findAll();

    boolean exists(K key);

    void delete(K key);

    void clear();
}
