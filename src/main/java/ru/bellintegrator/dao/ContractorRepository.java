package ru.bellintegrator.dao;

import ru.bellintegrator.model.Contractor;

public interface ContractorRepository {

    Contractor findById(int id);

    Contractor create(Contractor contractor);

    Contractor update(Contractor contractor);

    void delete(int id);
    void deleteAddress(int id);
    void deletePerson(int id);
}
