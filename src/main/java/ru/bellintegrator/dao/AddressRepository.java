package ru.bellintegrator.dao;

import ru.bellintegrator.model.Address;

public interface AddressRepository {
    Address findAddressById(int id);
}
