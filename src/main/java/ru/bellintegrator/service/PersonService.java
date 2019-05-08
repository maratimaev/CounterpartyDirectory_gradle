package ru.bellintegrator.service;

import ru.bellintegrator.model.Person;

public interface PersonService {
    Person getPerson(int id);
    Person create(Person person);
    Person update(Person person);
    void delete(int id);
}
