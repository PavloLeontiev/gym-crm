package com.paul.storage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class GenericStorage<ID extends Number, T> implements InitializeStorage<T>, Storage<ID, T> {

    protected Map<ID, T> storage = new ConcurrentHashMap<ID, T>();
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public  ID save(T entity) {
        Objects.requireNonNull(entity, getEntityClass().getName() + " cannot be null");
        ID id = (ID) Long.valueOf(counter.incrementAndGet());
        storage.put(id, entity);
        return id;
    }

    @Override
    public T findById(ID id) {
        return storage.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean delete(ID id) {
//        Objects.requireNonNull(id, "ID cannot be null");
        storage.remove(id);
        return false;
    }

    @Override
    public boolean exists(ID id) {
//        Objects.requireNonNull(id, "ID cannot be null");
        return storage.containsKey(id);
    }
}

