package com.paul.storage;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface Storage<K> {

    <T> void save(Class<T> type, K key, T value);

    <T> Optional<T> findByKey(Class<T> type, K key);

    <T> Collection<T> findAll(Class<T> type);

    <T> boolean exists(Class<T> type, K key);

    <T> void delete(Class<T> type, K key);

    void clear();
}
