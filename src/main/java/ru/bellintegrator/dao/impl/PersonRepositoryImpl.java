package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.db.tables.records.PersonRecord;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.model.mapper.MapperFacade;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DSLContext dsl;

    @Cacheable(value="personCache")
    @Override
    public Person findPersonById(int id) {
        return dsl
                .selectFrom(Tables.PERSON)
                .where(Tables.PERSON.ID.eq(id))
                .fetchOne()
                .map(e -> mapperFacade.map(e, Person.class));
    }

    @Override
    public Person create(Person person) {
        PersonRecord personRecord = dsl.insertInto(Tables.PERSON)
                .set(Tables.PERSON.LAST_NAME, person.getLastName())
                .set(Tables.PERSON.FIRST_NAME, person.getFirstName())
                .set(Tables.PERSON.MIDDLE_NAME, person.getMiddleName())
                .set(Tables.PERSON.PHONE_NUMBER, person.getPhoneNumber())
                .set(Tables.PERSON.EMAIL, person.getEmail())
                .returning(Tables.PERSON.ID)
                .fetchOne();
        person.setId(personRecord.getId());
        return person;
    }

    @CachePut(value = "personCache", key = "#person.id")
    @Override
    public Person update(Person person) {
        dsl.update(Tables.PERSON)
                .set(Tables.PERSON.LAST_NAME, person.getLastName())
                .set(Tables.PERSON.FIRST_NAME, person.getFirstName())
                .set(Tables.PERSON.MIDDLE_NAME, person.getMiddleName())
                .set(Tables.PERSON.PHONE_NUMBER, person.getPhoneNumber())
                .set(Tables.PERSON.EMAIL, person.getEmail())
                .where(Tables.PERSON.ID.equal(person.getId()))
                .execute();
        return findPersonById(person.getId());
    }

    @CacheEvict(value = "personCache")
    @Override
    public void delete(int id) {
        dsl.deleteFrom(Tables.PERSON)
                .where(Tables.PERSON.ID.equal(id))
                .execute();
    }
}
