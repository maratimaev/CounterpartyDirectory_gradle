package ru.bellintegrator.service;

import ru.bellintegrator.view.PersonView;

import java.util.List;

public interface PersonService {
    PersonView getPerson(int id);
    List<PersonView> getPersonsByContractor(int contractorId);
    PersonView create(PersonView personView, int contractorId);
    PersonView update(PersonView personView, int contractorId);
    void delete(int id, int contractorId);
}
