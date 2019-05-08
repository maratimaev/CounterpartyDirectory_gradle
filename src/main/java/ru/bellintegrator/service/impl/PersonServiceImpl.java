package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Person getPerson(int id) {
        return personRepository.findPersonById(id);
    }

    @Transactional
    public Person create(Person person) {
        return personRepository.create(person);
    }

    @Transactional
    public Person update(Person person) {
        return personRepository.update(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.delete(id);
    }
}
