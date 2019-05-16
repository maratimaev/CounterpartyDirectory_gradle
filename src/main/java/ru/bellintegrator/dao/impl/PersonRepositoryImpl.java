package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.db.tables.records.PersonRecord;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.model.mapper.MapperFacade;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DSLContext dsl;

    @Cacheable(value="personCache")
    @Override
    public Person findById(Integer id) {
        PersonRecord personRecord = dsl
                .selectFrom(Tables.PERSON)
                .where(Tables.PERSON.ID.eq(id))
                .fetchOne();
        return mapperFacade.map(personRecord, Person.class);
    }

    @Cacheable(value="personCache", key = "'contractorId:' + #contractorId")
    @Override
    public List<Person> findByContractorId(Integer contractorId) {
        List<PersonRecord> personRecordList = dsl
                .selectFrom(Tables.PERSON)
                .where(Tables.PERSON.CONTRACTOR_ID.eq(contractorId))
                .fetch();
        return mapperFacade.mapAsList(personRecordList, Person.class);
    }

    @CacheEvict(value = "personCache", key = "'contractorId:' + #contractorId")
    @Override
    public Person create(Person person, Integer contractorId) {
        PersonRecord personRecord = dsl.insertInto(Tables.PERSON)
                .set(Tables.PERSON.LAST_NAME, person.getLastName())
                .set(Tables.PERSON.FIRST_NAME, person.getFirstName())
                .set(Tables.PERSON.MIDDLE_NAME, person.getMiddleName())
                .set(Tables.PERSON.PHONE_NUMBER, person.getPhoneNumber())
                .set(Tables.PERSON.EMAIL, person.getEmail())
                .set(Tables.PERSON.PERSON_TYPE, person.getPersonType())
                .set(Tables.PERSON.CONTRACTOR_ID, contractorId)
                .returning(Tables.PERSON.ID)
                .fetchOne();
        person.setId(personRecord.getId());
        return person;
    }

    @CachePut(value = "personCache", key = "#person.id")
    @CacheEvict(value = "personCache", key = "'contractorId:' + #contractorId")
    @Override
    public Person update(Person person, Integer contractorId) {
        dsl.update(Tables.PERSON)
                .set(Tables.PERSON.LAST_NAME, person.getLastName())
                .set(Tables.PERSON.FIRST_NAME, person.getFirstName())
                .set(Tables.PERSON.MIDDLE_NAME, person.getMiddleName())
                .set(Tables.PERSON.PHONE_NUMBER, person.getPhoneNumber())
                .set(Tables.PERSON.EMAIL, person.getEmail())
                .where(Tables.PERSON.ID.equal(person.getId()))
                .execute();
        return this.findById(person.getId());
    }

    @Caching(evict = {
            @CacheEvict(value = "personCache", key = "#id"),
            @CacheEvict(value = "personCache", key = "'contractorId:' + #contractorId")
    })
    @Override
    public void delete(Integer id, Integer contractorId) {
        dsl.deleteFrom(Tables.PERSON)
                .where(Tables.PERSON.ID.equal(id))
                .execute();
    }
}
