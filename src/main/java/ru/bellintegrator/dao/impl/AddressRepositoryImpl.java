package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.AddressRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.model.mapper.MapperFacade;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    DSLContext dsl;

    public Address findAddressById(int id) {
        return dsl
                .selectFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.ID.eq(id))
                .fetchOne()
                .map(e -> mapperFacade.map(e, Address.class));
    }
}
