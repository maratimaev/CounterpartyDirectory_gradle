package ru.bellintegrator.dao;

import ru.bellintegrator.model.Person;

import java.util.List;

/**
 * DAO контактов
 */
public interface PersonRepository {
    /** Поиск контакта по id
     * @param id контакта
     * @return модель контакта
     */
    Person findById(Integer id);

    /** Поиск контактов по id контрагента
     * @param contractorId id контрагента
     * @return список моделей контактов
     */
    List<Person> findByContractorId(Integer contractorId);

    /** Создание контакта
     * @param person модель контакта
     * @param contractorId id контрагента для привязки
     * @return созданная модель контакта
     */
    Person create(Person person, Integer contractorId);

    /** Изменение контакта
     * @param person модель контакта
     * @param contractorId id контрагента
     * @return измененая модель контакта
     */
    Person update(Person person, Integer contractorId);

    /** Удаление контакта
     * @param id контакта
     * @param contractorId id контрагента
     */
    void delete(Integer id, Integer contractorId);
}
