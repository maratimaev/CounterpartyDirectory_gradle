package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.service.PersonService;

@Service
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private PersonService personService;

    @Transactional(readOnly = true)
    public Contractor getContractor(int id) {
        Contractor contractor = contractorRepository.findContractorById(id);
        contractor.setContacts(personService.getPersonsByContractor(id));
        return contractor;
    }
}
