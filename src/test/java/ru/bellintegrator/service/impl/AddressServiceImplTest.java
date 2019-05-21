package ru.bellintegrator.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.model.types.AddressType;
import ru.bellintegrator.service.AddressService;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.view.AddressView;
import ru.bellintegrator.view.ContractorView;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private BaseService<ContractorView> contractorService;

    private ContractorView testContractorView;
    private AddressView testAddressView;

    @Before
    public void createTestEnvironment() {
        createTestContractor();
        createTestAddress();
    }

    @After
    public void deleteTestEnvironment() {
        deleteTestContractor();
        deleteTestAddress();
    }

    @Test
    public void checkSuccessGetByContractorId() {
        List<AddressView> avList = addressService.getByContractorId(this.testContractorView.getId());
        assertThat(avList.contains(this.testAddressView), is(true));
    }

    @Test
    public void checkSuccessGetByTypeAndContractorId() {
        AddressView av = addressService.getByTypeAndContractorId(AddressType.Legal.getValue(), this.testContractorView.getId());
        assertThat(av, is(this.testAddressView));
    }

    @Test
    public void checkSuccessUpdate() {
        this.testAddressView.setCity(RandomStringUtils.random(8, true, true));
        AddressView av = addressService.update(this.testAddressView, this.testAddressView.getContractorId());
        assertThat(av.getCity(), is(this.testAddressView.getCity()));
    }

    @Test
    public void checkNullResultGetByWrongId() {
        assertThat(addressService.getById(-1), is(nullValue()));
    }

    @Test
    public void checkEmptyResultGetByWrongContractorId() {
        assertThat(addressService.getByContractorId(-1).isEmpty(), is(true));
    }

    @Test
    public void checkNullResultGetByWrongTypeAndContractorId() {
        assertThat(addressService.getByTypeAndContractorId(-1, -1), is(nullValue()));
    }

    private void createTestContractor() {
        ContractorView testContractor = new ContractorView(
                0,
                RandomStringUtils.random(8, true, true),
                RandomStringUtils.random(18, true, true),
                RandomStringUtils.random(10, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(15, true, true)
        );
        this.testContractorView = contractorService.create(testContractor, testContractor.getId());
        assertThat(contractorService.getById(this.testContractorView.getId()).getName(), is(testContractor.getName()));
    }

    private void createTestAddress() {
        AddressView testAddress = new AddressView(
                0,
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, true),
                Integer.parseInt(RandomStringUtils.random(3, false, true)),
                Integer.parseInt(RandomStringUtils.random(2, false, true)),
                AddressType.Legal.getValue(), this.testContractorView.getId()
        );
        this.testAddressView = addressService.create(testAddress, testAddress.getContractorId());
        assertThat(addressService.getById(this.testAddressView.getId()).getCity(), is(testAddress.getCity()));
    }

    private void deleteTestContractor() {
        contractorService.delete(this.testContractorView.getId(), this.testContractorView.getId());
        assertThat(contractorService.getById(this.testContractorView.getId()), is(nullValue()));
    }

    private void deleteTestAddress() {
        addressService.delete(this.testAddressView.getId(), this.testAddressView.getContractorId());
        assertThat(addressService.getById(this.testAddressView.getId()), is(nullValue()));
    }
}