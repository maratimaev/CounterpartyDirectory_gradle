package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.AddressRepository;
import ru.bellintegrator.exception.CantManipulateObject;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.service.AddressService;
import ru.bellintegrator.view.AddressView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class AddressServiceImpl  implements AddressService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public AddressView getById(int id) {
        return mapperFacade.map(addressRepository.findById(id), AddressView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<AddressView> getByContractorId(int contractorId) {
        return mapperFacade.mapAsList(addressRepository.findByContractorId(contractorId), AddressView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public AddressView getByTypeAndContractorId(int addressType, int contractorId) {
        return mapperFacade.map(addressRepository.findByTypeAndContractorId(addressType, contractorId), AddressView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public AddressView create(AddressView addressView, int contractorId) {
        Address address = null;
        if(addressView != null) {
            address = addressRepository.findByTypeAndContractorId(addressView.getAddressType(), contractorId);
            if (address == null) {
                address = addressRepository.create(mapperFacade.map(addressView, Address.class), contractorId);
            }
        }
        return mapperFacade.map(address, AddressView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public AddressView update(AddressView addressView, int contractorId) {
        Address updatedAddress = null;
        if(addressView != null) {
            Address address = addressRepository.findByTypeAndContractorId(addressView.getAddressType(), contractorId);
            if (address == null) {
                throw new CantManipulateObject(String.format("Can not find address by addressType=%s and contractorId=%s", addressView.getAddressType(), contractorId));
            }
            try {
                updatedAddress = addressRepository.update(mapperFacade.map(addressView, Address.class), contractorId);
            } catch (Exception ex) {
                throw new CantManipulateObject("There is a problem while updating address to DB", ex);
            }
        }
        return mapperFacade.map(updatedAddress, AddressView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void delete(int id, int contractorId) {
        addressRepository.delete(id);
    }
}
