package com.paul.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {

    T save(T model);
    T findById(ID id);
    List<T> findAll();
    void update(T model);
    boolean delete(T model);
}
