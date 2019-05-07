package ru.bellintegrator.model;

public class Address {
    private int id;
    private String region;
    private String city;
    private String street;
    private int houseNumber;
    private int housingNumber;

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
}
