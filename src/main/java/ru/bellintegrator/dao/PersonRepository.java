package ru.bellintegrator.dao;

import ru.bellintegrator.model.Person;

public interface PersonRepository {
    Person findPersonById(int id);
    Person create(Person person);
    Person update(Person person);
    void delete(int id);
}
