package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.exception.CantManipulateObject;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.PersonView;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContractorService contractorService;

    @Transactional(readOnly = true)
    public PersonView getPerson(int id) {
        try {
            return mapperFacade.map(personRepository.findById(id), PersonView.class);
        } catch (Exception ex) {
            throw new CantManipulateObject(String.format("There is a problem while finding contact by id=%s", id), ex);
        }
    }

    @Transactional(readOnly = true)
    public List<PersonView> getPersonsByContractor(int contractorId) {
        try {
            return mapperFacade.mapAsList(personRepository.findPersonsByContractor(contractorId), PersonView.class);
        } catch (Exception ex) {
            throw new CantManipulateObject(String.format("There is a problem while finding contacts by contractor id=%s", contractorId), ex);
        }
    }

    @Transactional
    public PersonView create(PersonView personView, int contractorId) {
        Person person = mapperFacade.map(personView, Person.class);
        try {
            return mapperFacade.map(personRepository.create(person, contractorId), PersonView.class);
        } catch (Exception ex) {
            throw new CantManipulateObject("There is a problem while saving new address to DB (check same object existence)", ex);
        }
    }

    @Transactional
    public PersonView update(PersonView personView, int contractorId) {
        Person person = mapperFacade.map(personView, Person.class);
        try {
            return mapperFacade.map(personRepository.create(person, contractorId), PersonView.class);
        } catch (Exception ex) {
            throw new CantManipulateObject("There is a problem while updating address to DB (check same object existence)", ex);
        }
    }

    @Transactional
    public void delete(int id, int contractorId) {
        contractorService.deletePerson(id);
        personRepository.delete(id, contractorId);
    }
}
