package com.paul.storage;

import java.util.List;

public interface Storage<ID, T> {

    ID save(T entity);

    T findById(ID id);

    List<T> findAll();

    boolean delete(ID id);

    boolean exists(ID id);
}
