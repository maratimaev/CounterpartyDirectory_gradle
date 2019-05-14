package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.model.mapper.MapperFacade;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.service.CrudService;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.AddressView;
import ru.bellintegrator.view.ContractorView;

@Service
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private CrudService<AddressView> addressService;

    @Transactional(readOnly = true)
    public ContractorView get(int id) {
        ContractorView contractorView = mapperFacade.map(contractorRepository.findById(id), ContractorView.class);
        contractorView.setContacts(personService.getPersonsByContractor(id));
        return contractorView;
    }

    @Override
    public ContractorView create(ContractorView contractorView) {
        Contractor contractor = contractorRepository.create(mapperFacade.map(contractorView, Contractor.class));
        Address legalAddress = mapperFacade.map(addressService.create(contractorView.getLegalAddress()), Address.class);
        Address advertising = mapperFacade.map(addressService.create(contractorView.getAdvertising()), Address.class);
        Person responsible = mapperFacade.map(personService.create(contractorView.getResponsible(), contractor.getId()), Person.class);
        contractor.setLegalAddress(legalAddress);
        contractor.setAdvertising(advertising);
        contractor.setResponsible(responsible);
        ContractorView contractorView1 = mapperFacade.map(contractorRepository.update(contractor), ContractorView.class);
        return contractorView1;
    }

    @Override
    public ContractorView update(ContractorView contractorView) {
        return null;
    }

    @Override
    public void delete(int id) {
        contractorRepository.delete(id);
    }

    @Override
    public void deleteAddress(int id) {
        contractorRepository.deleteAddress(id);
    }

    @Override
    public void deletePerson(int id) {
        contractorRepository.deletePerson(id);
    }
}
