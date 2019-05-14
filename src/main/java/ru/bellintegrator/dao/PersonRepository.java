package ru.bellintegrator.dao;

import ru.bellintegrator.model.Person;

import java.util.List;

public interface PersonRepository {
    Person findById(int id);
    List<Person> findPersonsByContractor(int contractorId);
    Person create(Person person, int contractorId);
    Person update(Person person, int contractorId);
    void delete(int id, int contractorId);
}
