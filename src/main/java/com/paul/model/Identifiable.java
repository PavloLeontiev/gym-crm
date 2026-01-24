package com.paul.model;

public interface Identifiable<K> {

    K getId();

    void setId(K id);
}
