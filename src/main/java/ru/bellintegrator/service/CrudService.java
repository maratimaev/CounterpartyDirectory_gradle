package ru.bellintegrator.service;

public interface CrudService <T> {
    T get(int id);

    T create(T t);

    T update(T t);

    void delete(int id);
}
