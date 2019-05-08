package ru.bellintegrator.service;

import ru.bellintegrator.model.Person;

import java.util.List;

public interface PersonService {
    Person getPerson(int id);
    List<Person> getPersonsByContractor(int contractorId);
    Person create(Person person, int contractorId);
    Person update(Person person, int contractorId);
    void delete(int id, int contractorId);
}
