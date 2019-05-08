package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.AddressRepository;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.db.tables.records.ContractorRecord;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.model.mapper.MapperFacade;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ContractorRepositoryImpl implements ContractorRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DSLContext dsl;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Contractor findContractorById(int id) {
        ContractorRecord contractorRecord = dsl
                .selectFrom(Tables.CONTRACTOR)
                .where(Tables.CONTRACTOR.ID.eq(id))
                .fetchOne();
        Contractor contractor = mapperFacade.map(contractorRecord, Contractor.class);
        contractor.setLegalAddress(addressRepository.findAddressById(contractorRecord.getLegalAddressId()));
        contractor.setAdvertising(addressRepository.findAddressById(contractorRecord.getAdvertisingId()));
        contractor.setResponsible(personRepository.findPersonById(contractorRecord.getResponsibleId()));

        List<Person> contacts = dsl
                .selectFrom(Tables.CONTRACTOR_PERSON)
                .where(Tables.CONTRACTOR_PERSON.CONTRACTOR_ID.eq(id))
                .fetch()
                .stream()
                .map(e -> mapperFacade.map(personRepository.findPersonById(e.getPersonId()), Person.class))
                .collect(Collectors.toList());
        contractor.setContacts(contacts);

        return contractor;
    }
}
