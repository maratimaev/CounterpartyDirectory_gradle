package ru.bellintegrator.dao;

import ru.bellintegrator.model.Address;

import java.util.List;

public interface AddressRepository {

    Address findById(Integer id);
    Address findByTypeAndContractorId(Integer addressType, Integer contractorId);
    List<Address> findByContractorId(Integer contractorId);
    Address create(Address address, Integer contractorId);
    Address update(Address address, Integer contractorId);
    void delete(Integer id);
}
