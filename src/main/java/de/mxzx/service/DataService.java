package de.mxzx.service;

import java.util.List;

public interface DataService<T> {

    boolean exists(String id);
    List<T> loadAll();
    T load(String id);
    void save(T type);
    void update(T type);

    void delete(String id);
}
