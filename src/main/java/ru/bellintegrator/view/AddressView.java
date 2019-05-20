package ru.bellintegrator.view;

import java.util.Objects;

/**
 * DTO адреса
 */
public class AddressView implements BaseView{
    /**
     * id адреса
     */
    private int id;
    /**
     * Регион
     */
    private String region;
    /**
     * Город
     */
    private String city;
    /**
     * Улица
     */
    private String street;
    /**
     * Номер дома
     */
    private int houseNumber;
    /**
     * Номер корпуса
     */
    private int housingNumber;
    /**
     * Тип адреса (1 - юридический, 2 - для рекламной продукции)
     */
    private int addressType;
    /**
     * id контрагента
     */
    private int contractorId;

    public AddressView() {
    }

    public AddressView(int id, String region, String city, String street, int houseNumber, int housingNumber, int addressType, int contractorId) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.housingNumber = housingNumber;
        this.addressType = addressType;
        this.contractorId = contractorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getHousingNumber() {
        return housingNumber;
    }

    public void setHousingNumber(int housingNumber) {
        this.housingNumber = housingNumber;
    }

    @Override
    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressView that = (AddressView) o;
        return id == that.id &&
                houseNumber == that.houseNumber &&
                housingNumber == that.housingNumber &&
                addressType == that.addressType &&
                contractorId == that.contractorId &&
                Objects.equals(region, that.region) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, city, street, houseNumber, housingNumber, addressType, contractorId);
    }
}
