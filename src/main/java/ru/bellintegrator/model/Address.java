package ru.bellintegrator.model;

/**
 * Модель адреса
 */
public class Address {
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
}
