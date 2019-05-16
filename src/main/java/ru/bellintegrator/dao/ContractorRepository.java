package ru.bellintegrator.dao;

import ru.bellintegrator.model.Contractor;

public interface ContractorRepository {

    Contractor findById(Integer id);
    Contractor create(Contractor contractor);
    Contractor update(Contractor contractor);
    void delete(Integer id);
}
