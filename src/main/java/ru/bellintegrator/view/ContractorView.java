package ru.bellintegrator.view;

import java.util.List;

public class ContractorView {
    private int id;
    private String name;
    private String fullName;
    private String type;
    private String inn;
    private AddressView legalAddress;
    private String phoneNumber;
    private AddressView advertising;
    private String email;
    private List<PersonView> contacts;
    private PersonView responsible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PersonView> getContacts() {
        return contacts;
    }

    public void setContacts(List<PersonView> contacts) {
        this.contacts = contacts;
    }

    public AddressView getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(AddressView legalAddress) {
        this.legalAddress = legalAddress;
    }

    public AddressView getAdvertising() {
        return advertising;
    }

    public void setAdvertising(AddressView advertising) {
        this.advertising = advertising;
    }

    public PersonView getResponsible() {
        return responsible;
    }

    public void setResponsible(PersonView responsible) {
        this.responsible = responsible;
    }
}
