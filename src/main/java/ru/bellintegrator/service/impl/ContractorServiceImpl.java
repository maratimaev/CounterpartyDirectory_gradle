package ru.bellintegrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.service.ContractorService;

@Service
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    @Transactional(readOnly = true)
    public Contractor getContractor(int id) {
        return contractorRepository.findContractorById(id);
    }
}
