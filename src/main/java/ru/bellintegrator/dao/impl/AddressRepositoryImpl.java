package ru.bellintegrator.dao.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.dao.AddressRepository;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.db.tables.records.AddressRecord;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.model.mapper.MapperFacade;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    DSLContext dsl;

    @Override
    public Address findById(Integer id) {
        AddressRecord addressRecord = dsl
                .selectFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.ID.eq(id))
                .fetchOne();
        return mapperFacade.map(addressRecord, Address.class);
    }

    @Override
    public Address findByTypeAndContractorId(Integer addressType, Integer contractorId) {
        AddressRecord addressRecord = dsl
                .selectFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.ADDRESS_TYPE.eq(addressType))
                .and(Tables.ADDRESS.CONTRACTOR_ID.eq(contractorId))
                .fetchOne();
        return mapperFacade.map(addressRecord, Address.class);
    }

    @Override
    public List<Address> findByContractorId(Integer contractorId) {
        List<AddressRecord> addressRecordList = dsl
                .selectFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.CONTRACTOR_ID.eq(contractorId))
                .fetch();
        return mapperFacade.mapAsList(addressRecordList, Address.class);
    }

    @Override
    public Address create(Address address, Integer contractorId) {
        AddressRecord addressRecord = dsl.insertInto(Tables.ADDRESS)
                .set(Tables.ADDRESS.REGION, address.getRegion())
                .set(Tables.ADDRESS.CITY, address.getCity())
                .set(Tables.ADDRESS.STREET, address.getStreet())
                .set(Tables.ADDRESS.HOUSE_NUMBER, address.getHouseNumber())
                .set(Tables.ADDRESS.HOUSING_NUMBER, address.getHousingNumber())
                .set(Tables.ADDRESS.ADDRESS_TYPE, address.getAddressType())
                .set(Tables.ADDRESS.CONTRACTOR_ID, contractorId)
                .returning(Tables.ADDRESS.ID)
                .fetchOne();
        address.setId(addressRecord.getId());
        return address;
    }

    @Override
    public Address update(Address address, Integer contractorId) {
        dsl.update(Tables.ADDRESS)
                .set(Tables.ADDRESS.REGION, address.getRegion())
                .set(Tables.ADDRESS.CITY, address.getCity())
                .set(Tables.ADDRESS.STREET, address.getStreet())
                .set(Tables.ADDRESS.HOUSE_NUMBER, address.getHouseNumber())
                .set(Tables.ADDRESS.HOUSING_NUMBER, address.getHousingNumber())
                .where(Tables.ADDRESS.ID.equal(address.getId()))
                .execute();
        return this.findByTypeAndContractorId(address.getAddressType(), contractorId);
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.ID.equal(id))
                .execute();
    }
}
