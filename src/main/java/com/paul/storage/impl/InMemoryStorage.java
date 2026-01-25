package com.paul.storage.impl;

import com.paul.model.Identifiable;
import com.paul.model.UsernameIndex;
import com.paul.storage.Storage;
import com.paul.storage.index.UsernameIndexStorage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryStorage implements Storage<Long>, UsernameIndexStorage<Long> {

    private final Map<Long, Object> storage = new ConcurrentHashMap<>();
    private final Map<String, Long> usernameIndexStorage = new ConcurrentHashMap<>();

    private final AtomicLong counter = new AtomicLong();

    @Override
    public Long save(Object model) {
        Long id = counter.incrementAndGet();

        if (model instanceof Identifiable identifiableModel) {
            identifiableModel.setId(id);
        }

        if (model instanceof UsernameIndex usernameIndex) {
            usernameIndexStorage.put(usernameIndex.getUsername(), id);
        }

        storage.put(id, model);
        return id;
    }

    @Override
    public Object findById(Long key) {
        return storage.get(key);
    }

    @Override
    public List<Object> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean exists(Long key) {
        return storage.containsKey(key);
    }

    @Override
    public void delete(Long key) {
        Object model = storage.get(key);
        if (model != null) {
            storage.remove(key);
        }
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Long findIdByUsername(String username) {
        return usernameIndexStorage.get(username);
    }

    @Override
    public Collection<String> getUsernames() {
        return usernameIndexStorage.keySet();
    }
}
