package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.exception.CantManipulateObject;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.model.types.PersonType;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.PersonView;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    @Override
    public PersonView getById(int id) {
        return mapperFacade.map(personRepository.findById(id), PersonView.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonView> getByContractorId(int contractorId) {
        return mapperFacade.mapAsList(personRepository.findByContractorId(contractorId), PersonView.class);
    }

    @Transactional
    @Override
    public PersonView create(PersonView personView,  int contractorId) {
        Person person = null;
        if (personView != null) {
            List<Person> personList = personRepository.findByContractorId(contractorId);
            if (personList.stream().anyMatch(e -> e.getPersonType() == PersonType.Responsible.getValue())) {
                throw new CantManipulateObject(String.format("Responsible for contractor with id=%s already exist", personView.getContractorId()));
            }
            try {
                person = personRepository.create(mapperFacade.map(personView, Person.class), contractorId);
            } catch (Exception ex) {
                throw new CantManipulateObject("There is a problem while saving new contact to DB", ex);
            }
        }
        return mapperFacade.map(person, PersonView.class);
    }

    @Transactional
    @Override
    public PersonView update(PersonView personView, int contractorId) {
        Person updatedPerson = null;
        if(personView != null) {
            List<Person> personList = personRepository.findByContractorId(contractorId);
            boolean hasExist = personList.stream().anyMatch(e -> e.getId() == personView.getId());
            if (!hasExist) {
                throw new CantManipulateObject(String.format("Nothing to update by contact id=%s and contractorId=%s", personView.getId(), contractorId));
            }
            try {
                updatedPerson = personRepository.update(mapperFacade.map(personView, Person.class), contractorId);
            } catch (Exception ex) {
                throw new CantManipulateObject("There is a problem while updating contact to DB", ex);
            }
        }
        return mapperFacade.map(updatedPerson, PersonView.class);
    }

    @Transactional
    @Override
    public void delete(int id, int contractorId) {
        personRepository.delete(id, contractorId);
    }
}
