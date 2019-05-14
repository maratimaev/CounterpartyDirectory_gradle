package ru.bellintegrator.service;

import ru.bellintegrator.view.ContractorView;

public interface ContractorService {
    ContractorView get(int id);

    ContractorView create(ContractorView contractorView);

    ContractorView update(ContractorView contractorView);

    void delete(int id);
    void deleteAddress(int id);
    void deletePerson(int id);
}
