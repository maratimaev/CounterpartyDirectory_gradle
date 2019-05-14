package ru.bellintegrator.dao;

public interface CrudRepository<T> {
    T findById(int id);

    T create(T t);

    T update(T t);

    void delete(int id);
}
