package ru.bellintegrator.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {

    /**
     * Порт web сервера. Генерируется случайным образом.
     */
    @LocalServerPort
    private int port;

    /**
     * Объект для взаимодействия с RESTful Web Service.
     */
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Test
    public void checkSuccessWhenGetContractorById() {
        ContractorView contractorView = createTestContractor();

        ArrayList<PersonView> cvList = new ArrayList<>();
        cvList.add(createTestPerson(contractorView, PersonType.Contact.getValue()));
        contractorView.setContacts(cvList);

        contractorView.setResponsible(createTestPerson(contractorView, PersonType.Responsible.getValue()));
        contractorView.setLegalAddress(createTestAddress(contractorView, AddressType.Legal.getValue()));
        contractorView.setAdvertising(createTestAddress(contractorView, AddressType.Advertising.getValue()));

        String url = String.format("http://localhost:%s/api/contractor/%s", port, contractorView.getId());
        ResponseEntity<ContractorView> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ContractorView>() {});
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(notNullValue()));
        ContractorView result = response.getBody();

        assertThat(result, is(notNullValue()));
        assertThat(result.getName(), is(contractorView.getName()));
        assertThat(result.getLegalAddress(), is(contractorView.getLegalAddress()));
        assertThat(result.getAdvertising(), is(contractorView.getAdvertising()));
        assertThat(result.getResponsible(), is(contractorView.getResponsible()));
        assertThat(result.getContacts(), is(contractorView.getContacts()));

        deleteTestContractor(contractorView);
        deleteTestPerson(contractorView.getResponsible());
        deleteTestPerson(contractorView.getContacts().get(0));
        deleteTestAddress(contractorView.getLegalAddress());
        deleteTestAddress(contractorView.getAdvertising());
    }

    @Test
    public void checkNullWhenGetContractorByWrongId() {
        int wrongId = -1;
        String url = String.format("http://localhost:%s/api/contractor/%s", port, wrongId);
        ResponseEntity<ContractorView> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ContractorView>() {});
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(nullValue()));
    }

    @Test
    public void checkSuccessWhenCreateContractor() {
        String saveUrl = String.format("http://localhost:%s/api/contractor/create", port);
        ContractorView contractorView = createContractorView();

        ArrayList<PersonView> cvList = new ArrayList<>();
        cvList.add(createPersonView(contractorView, PersonType.Contact.getValue()));
        contractorView.setContacts(cvList);

        contractorView.setResponsible(createPersonView(contractorView, PersonType.Responsible.getValue()));
        contractorView.setLegalAddress(createAddressView(contractorView, AddressType.Legal.getValue()));
        contractorView.setAdvertising(createAddressView(contractorView, AddressType.Advertising.getValue()));

        ResponseEntity<ContractorView> response = restTemplate.postForEntity(saveUrl, contractorView, ContractorView.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(notNullValue()));
        ContractorView result = response.getBody();
        assertThat(result.getName(), is(contractorView.getName()));
        assertThat(result.getLegalAddress().getCity(), is(contractorView.getLegalAddress().getCity()));
        assertThat(result.getAdvertising().getCity(), is(contractorView.getAdvertising().getCity()));
        assertThat(result.getResponsible().getLastName(), is(contractorView.getResponsible().getLastName()));
        assertThat(result.getContacts().get(0).getLastName(), is(contractorView.getContacts().get(0).getLastName()));

        deleteTestContractor(result);
        deleteTestPerson(result.getResponsible());
        deleteTestPerson(result.getContacts().get(0));
        deleteTestAddress(result.getLegalAddress());
        deleteTestAddress(result.getAdvertising());
    }

    @Test
    public void checkSuccessWhenUpdateContractor() {
        ContractorView cv = createTestContractor();
        ArrayList<PersonView> cvList = new ArrayList<>();
        cvList.add(createTestPerson(cv, PersonType.Contact.getValue()));
        cv.setContacts(cvList);
        cv.setResponsible(createTestPerson(cv, PersonType.Responsible.getValue()));
        cv.setLegalAddress(createTestAddress(cv, AddressType.Legal.getValue()));
        cv.setAdvertising(createTestAddress(cv, AddressType.Advertising.getValue()));

        String getUrl = String.format("http://localhost:%s/api/contractor/%s", port, cv.getId());
        ResponseEntity<ContractorView> response = restTemplate.exchange(
                getUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<ContractorView>() {});
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(notNullValue()));
        ContractorView result = response.getBody();

        assertThat(result, is(notNullValue()));
        assertThat(result.getName(), is(cv.getName()));
        assertThat(result.getLegalAddress(), is(cv.getLegalAddress()));
        assertThat(result.getAdvertising(), is(cv.getAdvertising()));
        assertThat(result.getResponsible(), is(cv.getResponsible()));
        assertThat(result.getContacts(), is(cv.getContacts()));

        cv.setName(RandomStringUtils.random(8, true, true));
        ArrayList<PersonView> cvUpdatedList = new ArrayList<>();
        cv.getContacts().get(0).setLastName(RandomStringUtils.random(8, true, true));
        cvUpdatedList.add(cv.getContacts().get(0));
        cv.setContacts(cvUpdatedList);
        cv.getResponsible().setLastName(RandomStringUtils.random(8, true, true));
        cv.getLegalAddress().setCity(RandomStringUtils.random(8, true, true));
        cv.getAdvertising().setCity(RandomStringUtils.random(8, true, true));

        String updateUrl = String.format("http://localhost:%s/api/contractor/update", port);
        HttpEntity<ContractorView> entity = new HttpEntity<>(cv);
        ResponseEntity<ContractorView> updateResponse = restTemplate.exchange(
                updateUrl, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<ContractorView>() {});

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(notNullValue()));
        ContractorView updatedResult = updateResponse.getBody();

        assertThat(updatedResult, is(notNullValue()));
        assertThat(updatedResult.getName(), is(cv.getName()));
        assertThat(updatedResult.getLegalAddress(), is(cv.getLegalAddress()));
        assertThat(updatedResult.getAdvertising(), is(cv.getAdvertising()));
        assertThat(updatedResult.getResponsible(), is(cv.getResponsible()));
        assertThat(updatedResult.getContacts(), is(cv.getContacts()));

        deleteTestContractor(cv);
        deleteTestPerson(cv.getResponsible());
        deleteTestPerson(cv.getContacts().get(0));
        deleteTestAddress(cv.getLegalAddress());
        deleteTestAddress(cv.getAdvertising());
    }

    @Test
    public void check500WhenUpdateByWrongResponsibleId() {
        ContractorView cv = createTestContractor();
        ArrayList<PersonView> cvList = new ArrayList<>();
        cvList.add(createTestPerson(cv, PersonType.Contact.getValue()));
        cv.setContacts(cvList);
        cv.setResponsible(createTestPerson(cv, PersonType.Responsible.getValue()));
        cv.setLegalAddress(createTestAddress(cv, AddressType.Legal.getValue()));
        cv.setAdvertising(createTestAddress(cv, AddressType.Advertising.getValue()));

        String getUrl = String.format("http://localhost:%s/api/contractor/%s", port, cv.getId());
        ResponseEntity<ContractorView> response = restTemplate.exchange(
                getUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<ContractorView>() {});
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(notNullValue()));
        ContractorView result = response.getBody();

        assertThat(result, is(notNullValue()));
        assertThat(result.getName(), is(cv.getName()));
        assertThat(result.getLegalAddress(), is(cv.getLegalAddress()));
        assertThat(result.getAdvertising(), is(cv.getAdvertising()));
        assertThat(result.getResponsible(), is(cv.getResponsible()));
        assertThat(result.getContacts(), is(cv.getContacts()));

        cv.setResponsible(createPersonView(cv,2));

        String updateUrl = String.format("http://localhost:%s/api/contractor/update", port);
        HttpEntity<ContractorView> entity = new HttpEntity<>(cv);
        ResponseEntity<ContractorView> updateResponse = restTemplate.exchange(
                updateUrl, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<ContractorView>() {});

        assertThat(updateResponse.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private ContractorView createTestContractor() {
        ContractorView testContractor =  contractorService.create(createContractorView(), 0);
        Assert.assertThat(contractorService.getById(testContractor.getId()).getName(), is(testContractor.getName()));
        return testContractor;
    }

    private ContractorView createContractorView() {
        return new ContractorView(
                0,
                RandomStringUtils.random(8, true, true),
                RandomStringUtils.random(18, true, true),
                RandomStringUtils.random(10, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(15, true, true)
        );
    }

    private PersonView createTestPerson(ContractorView contractorView, int type) {
        PersonView testContactPerson = personService.create(createPersonView(contractorView, type), contractorView.getContractorId());
        Assert.assertThat(personService.getById(testContactPerson.getId()).getLastName(), is(testContactPerson.getLastName()));
        return testContactPerson;
    }

    private PersonView createPersonView(ContractorView contractorView, int type) {
        return new PersonView(
                0,
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(15, true, false),
                RandomStringUtils.random(10, false, true),
                RandomStringUtils.random(10, true, false),
                type, contractorView.getId()
        );
    }

    private AddressView createTestAddress(ContractorView contractorView, int type) {
        AddressView testAddress = addressService.create(createAddressView(contractorView, type), contractorView.getId());
        Assert.assertThat(addressService.getById(testAddress.getId()).getCity(), is(testAddress.getCity()));
        return testAddress;
    }

    private AddressView createAddressView(ContractorView contractorView, int type) {
        return new AddressView(
                0,
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, false),
                RandomStringUtils.random(8, true, true),
                Integer.parseInt(RandomStringUtils.random(3, false, true)),
                Integer.parseInt(RandomStringUtils.random(2, false, true)),
                type, contractorView.getId()
        );
    }

    private void deleteTestContractor(ContractorView contractorView) {
        contractorService.delete(contractorView.getId(), contractorView.getId());
        Assert.assertThat(contractorService.getById(contractorView.getId()), is(nullValue()));
    }

    private void deleteTestPerson(PersonView personView) {
        personService.delete(personView.getId(), personView.getContractorId());
        Assert.assertThat(personService.getById(personView.getId()), is(nullValue()));
    }

    private void deleteTestAddress(AddressView addressView) {
        addressService.delete(addressView.getId(), addressView.getContractorId());
        Assert.assertThat(addressService.getById(addressView.getId()), is(nullValue()));
    }

}