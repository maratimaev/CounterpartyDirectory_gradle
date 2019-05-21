package ru.bellintegrator.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.model.types.PersonType;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.ContractorView;
import ru.bellintegrator.view.PersonView;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private BaseService<ContractorView> contractorService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ContractorView testContractorView;
    private PersonView testPersonView;

    @Before
    public void createTestEnvironment() {
        createTestContractor();
        createTestPerson();
    }

    @After
    public void deleteTestEnvironment() {
        deleteTestContractor();
        deleteTestPerson();
    }

    @Test
    public void successCheckGetById() {
        assertThat((personService.getById(this.testPersonView.getId()).getLastName()), is(testPersonView.getLastName()));
    }

    @Test
    public void checkSuccessGetByContractorId() {
        List<PersonView> pvList = personService.getByContractorId(this.testContractorView.getId());
        assertThat(pvList.contains(this.testPersonView), is(true));
    }

    @Test
    public void checkSuccessUpdate() {
        this.testPersonView.setLastName(RandomStringUtils.random(8, true, true));
        PersonView pv = personService.update(this.testPersonView, this.testPersonView.getContractorId());
        assertThat(pv.getLastName(), is(this.testPersonView.getLastName()));
    }

    @Test
    public void checkNullResultGetByWrongId() {
        assertThat(personService.getById(-1), is(nullValue()));
    }

    @Test
    public void checkEmptyResultGetByWrongContractorId() {
        assertThat(personService.getByContractorId(-1).isEmpty(), is(true));
    }

    @Test
    public void checkNullResultWhenCreateByNullPersonView() {
        assertThat(personService.create(null, 1), is(nullValue()));
    }

    @Test
    public void checkExceptionWhenCreateByWrongContractorId() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("There is a problem while saving new contact to DB");
        assertThat(personService.create(new PersonView(
                0,
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, true, false),
                PersonType.Contact.getValue(), this.testContractorView.getId()
        ), -1), is(nullValue()));
    }

    @Test
    public void checkExceptionWhenUpdateByWrongContractorId() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Nothing to update by contact");
        assertThat(personService.update(new PersonView(
                1,
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, true, false),
                PersonType.Contact.getValue(), this.testContractorView.getId()
        ), -1), is(nullValue()));
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
        PersonView testPerson = new PersonView(
                0,
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, true, false),
                PersonType.Contact.getValue(), this.testContractorView.getId()
        );
        this.testPersonView = personService.create(testPerson, testPerson.getContractorId());
        assertThat(personService.getById(this.testPersonView.getId()).getLastName(), is(testPerson.getLastName()));
    }

    private void deleteTestContractor() {
        contractorService.delete(this.testContractorView.getId(), this.testContractorView.getId());
        assertThat(contractorService.getById(this.testContractorView.getId()), is(nullValue()));
    }

    private void deleteTestPerson() {
        personService.delete(this.testPersonView.getId(), this.testPersonView.getContractorId());
        assertThat(personService.getById(this.testPersonView.getId()), is(nullValue()));
    }
}