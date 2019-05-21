package ru.bellintegrator.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.model.types.AddressType;
import ru.bellintegrator.model.types.PersonType;
import ru.bellintegrator.service.AddressService;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.AddressView;
import ru.bellintegrator.view.ContractorView;
import ru.bellintegrator.view.PersonView;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractorServiceImplTest {

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public TestName testName = new TestName();

    private ContractorView testContractorView;
    private PersonView testContactPersonView;
    private PersonView testResponsiblePersonView;
    private AddressView testLegalAddressView;
    private AddressView testAdvertisingAddressView;

    @Before
    public void createTestEnvironment() {
        createTestContractor();
        createTestAddress();
        createTestPerson();
    }

    @After
    public void deleteTestEnvironment() {
        deleteTestContractor();
        deleteTestAddress();
        deleteTestPerson();
    }

    @Test
    public void checkSuccessGetById() {
        ContractorView cv = contractorService.getById(this.testContractorView.getId());
        assertThat(cv.getName(), is(this.testContractorView.getName()));
        assertThat(cv.getLegalAddress(), is(this.testContractorView.getLegalAddress()));
        assertThat(cv.getAdvertising(), is(this.testContractorView.getAdvertising()));
        assertThat(cv.getResponsible(), is(this.testContractorView.getResponsible()));
        assertThat(cv.getContacts(), is(this.testContractorView.getContacts()));
    }

    @Test
    public void checkSuccessUpdate() {
        this.testContractorView.setName(RandomStringUtils.random(8, true, true));
        this.testLegalAddressView.setCity(RandomStringUtils.random(8, true, false));
        this.testAdvertisingAddressView.setCity(RandomStringUtils.random(8, true, false));
        this.testResponsiblePersonView.setLastName(RandomStringUtils.random(8, true, true));
        this.testContactPersonView.setLastName(RandomStringUtils.random(8, true, true));
        this.testContractorView.setLegalAddress(this.testLegalAddressView);
        this.testContractorView.setAdvertising(this.testAdvertisingAddressView);
        this.testContractorView.setResponsible(this.testResponsiblePersonView);
        ArrayList<PersonView> cvList = new ArrayList<>();
        cvList.add(this.testContactPersonView);
        this.testContractorView.setContacts(cvList);
        ContractorView cv = contractorService.update(this.testContractorView, this.testContractorView.getId());
        assertThat(cv.getName(), is(this.testContractorView.getName()));
        assertThat(cv.getLegalAddress(), is(this.testContractorView.getLegalAddress()));
        assertThat(cv.getAdvertising(), is(this.testContractorView.getAdvertising()));
        assertThat(cv.getResponsible(), is(this.testContractorView.getResponsible()));
        assertThat(cv.getContacts(), is(this.testContractorView.getContacts()));
    }

    @Test
    public void checkNullResultGetByWrongId() {
        assertThat(contractorService.getById(-1), is(nullValue()));
    }

    @Test
    public void checkExceptionWhenCreateNullPersonView() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Contractor can't be null");
        contractorService.create(null, -1);
    }

    @Test
    public void checkExceptionWhenUpdateNullPersonView() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Contractor can't be null");
        contractorService.update(null, -1);
    }

    @Test
    public void checkExceptionWhenUpdateWithWrongContractorId() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Can not find address by addressType");
        contractorService.update(this.testContractorView, -1);
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

    private void createTestPerson() {
        PersonView testContactPerson = new PersonView(
                0,
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, true, false),
                PersonType.Contact.getValue(), this.testContractorView.getId()
        );
        this.testContactPersonView = personService.create(testContactPerson, testContactPerson.getContractorId());
        assertThat(personService.getById(this.testContactPersonView.getId()).getLastName(), is(testContactPerson.getLastName()));
        ArrayList<PersonView> cvList = new ArrayList<>();
        cvList.add(this.testContactPersonView);
        this.testContractorView.setContacts(cvList);

        PersonView testResponsiblePerson = new PersonView(
                0,
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, true, false),
                PersonType.Responsible.getValue(), this.testContractorView.getId()
        );
        this.testResponsiblePersonView = personService.create(testResponsiblePerson, testResponsiblePerson.getContractorId());
        assertThat(personService.getById(this.testResponsiblePersonView.getId()).getLastName(), is(testResponsiblePerson.getLastName()));
        this.testContractorView.setResponsible(this.testResponsiblePersonView);
    }

    private void createTestAddress() {
        AddressView testLegalAddress = new AddressView(
                0,
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, true),
                Integer.parseInt(RandomStringUtils.random(3, false, true)),
                Integer.parseInt(RandomStringUtils.random(2, false, true)),
                AddressType.Legal.getValue(), this.testContractorView.getId()
        );
        this.testLegalAddressView = addressService.create(testLegalAddress, testLegalAddress.getContractorId());
        assertThat(addressService.getById(this.testLegalAddressView.getId()).getCity(), is(testLegalAddress.getCity()));
        this.testContractorView.setLegalAddress(this.testLegalAddressView);

        AddressView testAdvertisingAddress = new AddressView(
                0,
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, true),
                Integer.parseInt(RandomStringUtils.random(3, false, true)),
                Integer.parseInt(RandomStringUtils.random(2, false, true)),
                AddressType.Advertising.getValue(), this.testContractorView.getId()
        );
        this.testAdvertisingAddressView = addressService.create(testAdvertisingAddress, testAdvertisingAddress.getContractorId());
        assertThat(addressService.getById(this.testAdvertisingAddressView.getId()).getCity(), is(testAdvertisingAddress.getCity()));
        this.testContractorView.setAdvertising(this.testAdvertisingAddressView);
    }

    private void deleteTestContractor() {
        contractorService.delete(this.testContractorView.getId(), this.testContractorView.getId());
        assertThat(contractorService.getById(this.testContractorView.getId()), is(nullValue()));
    }

    private void deleteTestPerson() {
        personService.delete(this.testContactPersonView.getId(), this.testContactPersonView.getContractorId());
        assertThat(personService.getById(this.testContactPersonView.getId()), is(nullValue()));
        personService.delete(this.testResponsiblePersonView.getId(), this.testResponsiblePersonView.getContractorId());
        assertThat(personService.getById(this.testResponsiblePersonView.getId()), is(nullValue()));
    }

    private void deleteTestAddress() {
        addressService.delete(this.testLegalAddressView.getId(), this.testLegalAddressView.getContractorId());
        assertThat(addressService.getById(this.testLegalAddressView.getId()), is(nullValue()));
        addressService.delete(this.testAdvertisingAddressView.getId(), this.testAdvertisingAddressView.getContractorId());
        assertThat(addressService.getById(this.testAdvertisingAddressView.getId()), is(nullValue()));
    }
}