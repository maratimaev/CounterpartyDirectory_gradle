package ru.bellintegrator.service;

import ru.bellintegrator.view.PersonView;

import java.util.List;

public interface PersonService extends BaseService<PersonView>{
    List<PersonView> getByContractorId(int contractorId);
}
