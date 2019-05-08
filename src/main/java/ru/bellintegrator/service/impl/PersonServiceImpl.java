package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Person getPerson(int id) {
        return personRepository.findPersonById(id);
    }

    @Transactional(readOnly = true)
    public List<Person> getPersonsByContractor(int contractorId) {
        return personRepository.findPersonsByContractor(contractorId);
    }

    @Transactional
    public Person create(Person person, int contractorId) {
        return personRepository.create(person, contractorId);
    }

    @Transactional
    public Person update(Person person, int contractorId) {
        return personRepository.update(person, contractorId);
    }

    @Transactional
    public void delete(int id, int contractorId) {
        personRepository.delete(id, contractorId);
    }
}
