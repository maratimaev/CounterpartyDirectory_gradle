package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.PersonRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.model.mapper.MapperFacade;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    DSLContext dsl;

    public Person findPersonById(int id) {
        return dsl
                .selectFrom(Tables.PERSON)
                .where(Tables.PERSON.ID.eq(id))
                .fetchOne()
                .map(e -> mapperFacade.map(e, Person.class));
    }

    @Override
    public List<Person> findPersonsByContractor(int contractorId) {
        return dsl
                .selectFrom(Tables.CONTRACTOR_PERSON)
                .where(Tables.CONTRACTOR_PERSON.CONTRACTOR_ID.eq(contractorId))
                .fetch()
                .stream()
                .map(e -> mapperFacade.map(findPersonById(e.getPersonId()), Person.class))
                .collect(Collectors.toList());
    }
}
