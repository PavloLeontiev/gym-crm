package com.paul.storage.impl;

import com.paul.storage.Storage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStorage implements Storage<Long> {

    private final Map<Class<?>, Map<Long, Object>> storage =
            new ConcurrentHashMap<>();

    @Override
    public <T> void save(Class<T> type, Long key, T value) {
        storage
                .computeIfAbsent(type, k -> new ConcurrentHashMap<>())
                .put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> findByKey(Class<T> type, Long key) {
        return Optional.ofNullable(
                (T) storage
                        .getOrDefault(type, Map.of())
                        .get(key)
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Collection<T> findAll(Class<T> type) {
        return (Collection<T>) storage
                .getOrDefault(type, Map.of())
                .values();
    }

    @Override
    public <T> boolean exists(Class<T> type, Long key) {
        return storage
                .getOrDefault(type, Map.of())
                .containsKey(key);
    }

    @Override
    public <T> void delete(Class<T> type, Long key) {
        Map<Long, Object> map = storage.get(type);
        if (map != null) {
            map.remove(key);
        }
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
