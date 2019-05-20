package ru.bellintegrator.model;

/**
 * Модель контрагента
 */
public class Contractor {
    /**
     * id контрагента
     */
    private int id;
    /**
     * Имя контрагента
     */
    private String name;
    /**
     * Полное имя контрагента
     */
    private String fullName;
    /**
     * Тип контрагента
     */
    private String type;
    /**
     * ИНН
     */
    private String inn;
    /**
     * Номер телефона
     */
    private String phoneNumber;
    /**
     * Электронная почта
     */
    private String email;

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
}
