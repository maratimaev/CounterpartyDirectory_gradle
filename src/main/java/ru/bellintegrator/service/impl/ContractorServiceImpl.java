package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.exception.CantManipulateObject;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.service.AddressService;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.ContractorView;

import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    @Override
    public ContractorView getById(int id) {
        ContractorView contractorView = mapperFacade.map(contractorRepository.findById(id), ContractorView.class);
        contractorView.setLegalAddress(addressService.getByTypeAndContractorId(1, id));
        contractorView.setAdvertising(addressService.getByTypeAndContractorId(2, id));
        contractorView.setContacts(
                personService.getByContractorId(id)
                .stream().filter(e -> e.getPersonType() == 1)
                .collect(Collectors.toList())
        );
        personService.getByContractorId(id)
                .stream().filter(e -> e.getPersonType() == 2)
                .findFirst().ifPresent(contractorView::setResponsible);
        return contractorView;
    }

    @Transactional
    @Override
    public ContractorView create(ContractorView contractorView) {
        Contractor contractor = contractorRepository.create(mapperFacade.map(contractorView, Contractor.class));
        addressService.create(contractorView.getLegalAddress(), contractor.getId());
        addressService.create(contractorView.getAdvertising(), contractor.getId());
        personService.create(contractorView.getResponsible(), contractor.getId());
        contractorView.getContacts().forEach(e -> personService.create(e, contractor.getId()));
        return this.getById(contractor.getId());
    }

    @Transactional
    @Override
    public ContractorView update(ContractorView contractorView) {
        Contractor contractor = contractorRepository.update(mapperFacade.map(contractorView, Contractor.class));
        if(contractor == null) {
            throw new CantManipulateObject(String.format("Can't find contractor by id=%s", contractorView.getId()));
        }
        addressService.update(contractorView.getLegalAddress(), contractor.getId());
        addressService.update(contractorView.getAdvertising(), contractor.getId());
        personService.update(contractorView.getResponsible(), contractor.getId());
        contractorView.getContacts().forEach(e -> personService.update(e, contractor.getId()));
        return this.getById(contractor.getId());
    }

    @Transactional
    @Override
    public void delete(int id) {
        personService.getByContractorId(id).forEach(e -> personService.delete(e.getId(), id));
        addressService.getByContractorId(id).forEach(e -> addressService.delete(e.getId()));
        contractorRepository.delete(id);
    }
}
