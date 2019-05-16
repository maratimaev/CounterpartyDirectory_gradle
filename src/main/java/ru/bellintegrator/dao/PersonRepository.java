package ru.bellintegrator.dao;

import ru.bellintegrator.model.Person;

import java.util.List;

public interface PersonRepository {
    Person findById(Integer id);
    List<Person> findByContractorId(Integer contractorId);
    Person create(Person person, Integer contractorId);
    Person update(Person person, Integer contractorId);
    void delete(Integer id, Integer contractorId);
}
