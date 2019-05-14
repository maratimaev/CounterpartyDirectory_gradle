package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.CrudRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.db.tables.records.AddressRecord;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.model.mapper.MapperFacade;

@Repository
public class AddressRepositoryImpl implements CrudRepository<Address> {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    DSLContext dsl;

    public Address findById(int id) {
        return dsl
                .selectFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.ID.eq(id))
                .fetchOne()
                .map(e -> mapperFacade.map(e, Address.class));
    }

    @Override
    public Address create(Address address) {
        AddressRecord addressRecord = dsl.insertInto(Tables.ADDRESS)
                .set(Tables.ADDRESS.REGION, address.getRegion())
                .set(Tables.ADDRESS.CITY, address.getCity())
                .set(Tables.ADDRESS.STREET, address.getStreet())
                .set(Tables.ADDRESS.HOUSE_NUMBER, address.getHouseNumber())
                .set(Tables.ADDRESS.HOUSING_NUMBER, address.getHousingNumber())
                .returning(Tables.ADDRESS.ID)
                .fetchOne();
        address.setId(addressRecord.getId());
        return address;
    }

    @Override
    public Address update(Address address) {
        dsl.update(Tables.ADDRESS)
                .set(Tables.ADDRESS.REGION, address.getRegion())
                .set(Tables.ADDRESS.CITY, address.getCity())
                .set(Tables.ADDRESS.STREET, address.getStreet())
                .set(Tables.ADDRESS.HOUSE_NUMBER, address.getHouseNumber())
                .set(Tables.ADDRESS.HOUSING_NUMBER, address.getHousingNumber())
                .where(Tables.ADDRESS.ID.equal(address.getId()))
                .execute();
        return this.findById(address.getId());
    }

    @Override
    public void delete(int id) {
        dsl.deleteFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.ID.equal(id))
                .execute();
    }
}
