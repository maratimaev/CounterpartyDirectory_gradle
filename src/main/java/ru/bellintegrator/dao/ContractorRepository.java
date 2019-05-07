package ru.bellintegrator.dao;

import ru.bellintegrator.model.Contractor;

public interface ContractorRepository {
    Contractor findContractorById(int id);
}
