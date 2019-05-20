package ru.bellintegrator.view;

import java.util.Objects;

/**
 * DTO контакта
 */
public class PersonView implements BaseView{
    /**
     * id контакта
     */
    private int id;
    /**
     * Фамилия
     */
    private String lastName;
    /**
     * Имя
     */
    private String firstName;
    /**
     * Отчество
     */
    private String middleName;
    /**
     * Номер телефона
     */
    private String phoneNumber;
    /**
     * Электронная почта
     */
    private String email;
    /**
     * Тип контакта (1 - контактное лицо, 2 - ответственный)
     */
    private int personType;
    /**
     * id контрагента
     */
    private int contractorId;

    public PersonView() {
    }

    public PersonView(int id, String lastName, String firstName, String middleName, String phoneNumber, String email, int personType, int contractorId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.personType = personType;
        this.contractorId = contractorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    @Override
    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonView that = (PersonView) o;
        return id == that.id &&
                personType == that.personType &&
                contractorId == that.contractorId &&
                lastName.equals(that.lastName) &&
                firstName.equals(that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, phoneNumber, email, personType, contractorId);
    }
}
