package ru.bellintegrator.service;

import ru.bellintegrator.view.PersonView;

import java.util.List;

public interface PersonService {
    PersonView getById(int id);
    List<PersonView> getByContractorId(int contractorId);
    PersonView create(PersonView personView, int contractorId);
    PersonView update(PersonView personView, int contractorId);
    void delete(int id, int contractorId);
}
