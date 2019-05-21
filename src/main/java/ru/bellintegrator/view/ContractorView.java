package ru.bellintegrator.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * DTO контрагента
 */
public class ContractorView implements BaseView{
    /**
     * id контрагента
     */
    @ApiModelProperty(notes = "id контрагента")
    private int id;
    /**
     * Имя контрагента
     */
    @ApiModelProperty(notes = "Имя контрагента")
    private String name;
    /**
     * Полное имя контрагента
     */
    @ApiModelProperty(notes = "Полное имя контрагента")
    private String fullName;
    /**
     * Тип контрагента
     */
    @ApiModelProperty(notes = "Тип контрагента")
    private String type;
    /**
     * ИНН
     */
    @ApiModelProperty(notes = "ИНН")
    private String inn;
    /**
     * DTO  юридического адреса
     */
    @ApiModelProperty(notes = "DTO  юридического адреса")
    private AddressView legalAddress;
    /**
     * Номер телефона
     */
    @ApiModelProperty(notes = "Номер телефона")
    private String phoneNumber;
    /**
     * DTO адреса доставки рекламной продукции
     */
    @ApiModelProperty(notes = "DTO адреса доставки рекламной продукции")
    private AddressView advertising;
    /**
     * Электронная почта
     */
    @ApiModelProperty(notes = "Электронная почта")
    private String email;
    /**
     * Список DTO контактных лиц
     */
    @ApiModelProperty(notes = "Список DTO контактных лиц")
    private List<PersonView> contacts;
    /**
     * DTO ответственного лица
     */
    @ApiModelProperty(notes = "DTO ответственного лица")
    private PersonView responsible;

    public ContractorView() {
    }

    public ContractorView(int id, String name, String fullName, String type, String inn, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.type = type;
        this.inn = inn;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getContractorId() {
        return getId();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractorView that = (ContractorView) o;
        return id == that.id &&
                name.equals(that.name) &&
                fullName.equals(that.fullName) &&
                type.equals(that.type) &&
                inn.equals(that.inn) &&
                Objects.equals(legalAddress, that.legalAddress) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(advertising, that.advertising) &&
                Objects.equals(email, that.email) &&
                Objects.equals(contacts, that.contacts) &&
                Objects.equals(responsible, that.responsible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, type, inn, legalAddress, phoneNumber, advertising, email, contacts, responsible);
    }
}
