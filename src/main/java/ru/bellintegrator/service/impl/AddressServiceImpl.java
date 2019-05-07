package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.AddressRepository;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.service.AddressService;

@Service
public class AddressServiceImpl  implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public Address getAddress(int id) {
        return addressRepository.findAddressById(id);
    }
}
