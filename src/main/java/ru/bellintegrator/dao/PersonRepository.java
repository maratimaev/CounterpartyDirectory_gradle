package ru.bellintegrator.dao;

import ru.bellintegrator.model.Person;

import java.util.List;

public interface PersonRepository {
    Person findPersonById(int id);
    List<Person> findPersonsByContractor(int contractorId);
}
