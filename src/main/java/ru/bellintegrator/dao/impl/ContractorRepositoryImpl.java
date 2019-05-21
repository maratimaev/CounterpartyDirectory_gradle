package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.ContractorRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.db.tables.records.ContractorRecord;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.model.mapper.MapperFacade;

/**
 * {@inheritDoc}
 */
@Repository
public class ContractorRepositoryImpl implements ContractorRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DSLContext dsl;

    /**
     * {@inheritDoc}
     */
    @Override
    public Contractor findById(Integer id) {
        ContractorRecord contractorRecord = dsl
                .selectFrom(Tables.CONTRACTOR)
                .where(Tables.CONTRACTOR.ID.eq(id))
                .fetchOne();
        return mapperFacade.map(contractorRecord, Contractor.class);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Tables.CONTRACTOR)
            .where(Tables.CONTRACTOR.ID.equal(id))
                .execute();
    }
}
