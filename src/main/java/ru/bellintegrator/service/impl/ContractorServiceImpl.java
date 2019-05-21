package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.exception.CantManipulateObject;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.model.types.AddressType;
import ru.bellintegrator.model.types.PersonType;
import ru.bellintegrator.service.AddressService;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.ContractorView;
import ru.bellintegrator.view.PersonView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public ContractorView getById(int id) {
        ContractorView contractorView = mapperFacade.map(contractorRepository.findById(id), ContractorView.class);
        if (contractorView != null) {
            contractorView.setLegalAddress(addressService.getByTypeAndContractorId(AddressType.Legal.getValue(), id));
            contractorView.setAdvertising(addressService.getByTypeAndContractorId(AddressType.Advertising.getValue(), id));

            List<PersonView> pvList = personService.getByContractorId(id);
            contractorView.setContacts(
                    pvList.stream().filter(e -> e.getPersonType() == PersonType.Contact.getValue())
                            .collect(Collectors.toList())
            );
            pvList.stream().filter(e -> e.getPersonType() == PersonType.Responsible.getValue())
                    .findFirst().ifPresent(contractorView::setResponsible);
        }
        return contractorView;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public ContractorView create(ContractorView contractorView, int contractorId) {
        if(contractorView == null) {
            throw new CantManipulateObject("Contractor can't be null");
        }
        Contractor contractor = contractorRepository.create(mapperFacade.map(contractorView, Contractor.class));
        addressService.create(contractorView.getLegalAddress(), contractor.getId());
        addressService.create(contractorView.getAdvertising(), contractor.getId());
        personService.create(contractorView.getResponsible(), contractor.getId());
        List<PersonView> pvList = contractorView.getContacts();
        if(pvList != null) {
            pvList.forEach(e -> personService.create(e, contractor.getId()));
        }
        return this.getById(contractor.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public ContractorView update(ContractorView contractorView, int contractorId) {
        if(contractorView == null) {
            throw new CantManipulateObject("Contractor can't be null");
        }
        Contractor contractor = contractorRepository.update(mapperFacade.map(contractorView, Contractor.class));
        if(contractor == null) {
            throw new CantManipulateObject(String.format("Can't find contractor by id=%s", contractorView.getId()));
        }
        addressService.update(contractorView.getLegalAddress(), contractorId);
        addressService.update(contractorView.getAdvertising(), contractorId);
        personService.update(contractorView.getResponsible(), contractorId);
        contractorView.getContacts().forEach(e -> personService.update(e, contractor.getId()));
        return this.getById(contractor.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void delete(int id, int contractorId) {
        personService.getByContractorId(id).forEach(e -> personService.delete(e.getId(), id));
        addressService.getByContractorId(id).forEach(e -> addressService.delete(e.getId(), contractorId));
        contractorRepository.delete(id);
    }
}
