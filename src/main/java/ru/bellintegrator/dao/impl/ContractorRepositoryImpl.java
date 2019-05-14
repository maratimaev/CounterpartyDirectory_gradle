package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.dao.CrudRepository;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.db.tables.records.ContractorRecord;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.model.mapper.MapperFacade;

@Repository
public class ContractorRepositoryImpl implements ContractorRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DSLContext dsl;

    @Autowired
    private CrudRepository<Address> addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Contractor findById(int id) {
        ContractorRecord contractorRecord = dsl
                .selectFrom(Tables.CONTRACTOR)
                .where(Tables.CONTRACTOR.ID.eq(id))
                .fetchOne();
        Contractor contractor = mapperFacade.map(contractorRecord, Contractor.class);
        contractor.setLegalAddress(addressRepository.findById(contractorRecord.getLegalAddressId()));
        contractor.setAdvertising(addressRepository.findById(contractorRecord.getAdvertisingId()));
        contractor.setResponsible(personRepository.findById(contractorRecord.getResponsibleId()));
        return contractor;
    }

    @Override
    public Contractor create(Contractor contractor) {
        ContractorRecord contractorRecord = dsl.insertInto(Tables.CONTRACTOR)
                .set(Tables.CONTRACTOR.NAME, contractor.getName())
                .set(Tables.CONTRACTOR.FULL_NAME, contractor.getFullName())
                .set(Tables.CONTRACTOR.TYPE, contractor.getType())
                .set(Tables.CONTRACTOR.INN, contractor.getInn())
                .set(Tables.CONTRACTOR.PHONE_NUMBER, contractor.getPhoneNumber())
                .set(Tables.CONTRACTOR.EMAIL, contractor.getEmail())
                .returning(Tables.CONTRACTOR.ID)
                .fetchOne();
        contractor.setId(contractorRecord.getId());
        return contractor;
    }

    @Override
    public Contractor update(Contractor contractor) {
        dsl.update(Tables.CONTRACTOR)
                .set(Tables.CONTRACTOR.NAME, contractor.getName())
                .set(Tables.CONTRACTOR.FULL_NAME, contractor.getFullName())
                .set(Tables.CONTRACTOR.TYPE, contractor.getType())
                .set(Tables.CONTRACTOR.INN, contractor.getInn())
                .set(Tables.CONTRACTOR.PHONE_NUMBER, contractor.getPhoneNumber())
                .set(Tables.CONTRACTOR.EMAIL, contractor.getEmail())
                .where(Tables.CONTRACTOR.ID.equal(contractor.getId()))
                .execute();
        return this.findById(contractor.getId());
    }

    @Override
    public void delete(int id) {
        dsl.deleteFrom(Tables.CONTRACTOR)
            .where(Tables.CONTRACTOR.ID.equal(id))
                .execute();
    }

    @Override
    public void deleteAddress(int id) {
        dsl.update(Tables.CONTRACTOR)
                .set(Tables.CONTRACTOR.ADVERTISING_ID, (Integer) null)
                .where(Tables.CONTRACTOR.ADVERTISING_ID.equal(id))
                .execute();

        dsl.update(Tables.CONTRACTOR)
                .set(Tables.CONTRACTOR.LEGAL_ADDRESS_ID, (Integer) null)
                .where(Tables.CONTRACTOR.LEGAL_ADDRESS_ID.equal(id))
                .execute();
    }

    @Override
    public void deletePerson(int id) {
        dsl.update(Tables.CONTRACTOR)
                .set(Tables.CONTRACTOR.RESPONSIBLE_ID, (Integer) null)
                .where(Tables.CONTRACTOR.RESPONSIBLE_ID.equal(id))
                .execute();

        dsl.deleteFrom(Tables.CONTRACTOR_PERSON)
                .where(Tables.CONTRACTOR_PERSON.PERSON_ID.equal(id))
                .execute();
    }
}
