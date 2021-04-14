package org.example.mysqldemo.repository;

import java.util.List;

public interface GenericRepository<T> {

    // CRUD
    void save(T obj);
    void update(T obj);
    T get(Integer id);
    void delete(Integer id);
    List<T> list();

}
