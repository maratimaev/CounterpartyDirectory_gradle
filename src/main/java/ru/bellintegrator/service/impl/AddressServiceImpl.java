package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.CrudRepository;
import ru.bellintegrator.exception.CantManipulateObject;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.service.CrudService;
import ru.bellintegrator.view.AddressView;

@Service
public class AddressServiceImpl  implements CrudService<AddressView> {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private CrudRepository<Address> addressRepository;

    @Autowired
    private ContractorService contractorService;

    @Transactional(readOnly = true)
    public AddressView get(int id) {
        try {
            return mapperFacade.map(addressRepository.findById(id), AddressView.class);
        } catch (Exception ex) {
            throw new CantManipulateObject(String.format("There is a problem while finding address by id=%s", id), ex);
        }
    }

    @Transactional
    @Override
    public AddressView create(AddressView addressView) {
        Address address = mapperFacade.map(addressView, Address.class);
        try {
            return mapperFacade.map(addressRepository.create(address), AddressView.class);
        } catch (Exception ex) {
            throw new CantManipulateObject("There is a problem while saving new address to DB (check same object existence)", ex);
        }
    }

    @Transactional
    @Override
    public AddressView update(AddressView addressView) {
        Address address = mapperFacade.map(addressView, Address.class);
        try {
            return mapperFacade.map(addressRepository.update(address), AddressView.class);
        } catch (Exception ex) {
            throw new CantManipulateObject("There is a problem while updating address to DB (check same object existence)", ex);
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        contractorService.deleteAddress(id);
        addressRepository.delete(id);
    }
}
