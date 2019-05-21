package ru.bellintegrator.dao;

import ru.bellintegrator.model.Address;

import java.util.List;

/**
 * DAO адресов
 */
public interface AddressRepository {
    /** Поиск адреса по id
     * @param id адреса
     * @return модель адреса
     */
    Address findById(Integer id);

    /** Поиск адреса по типу адреса и id контрагента
     * @param addressType тип адреса
     * @param contractorId id контрагента
     * @return модель адреса
     */
    Address findByTypeAndContractorId(Integer addressType, Integer contractorId);

    /** Поиск адресов по id контрагента
     * @param contractorId id контрагента для привязки
     * @return список моделей адресов
     */
    List<Address> findByContractorId(Integer contractorId);

    /** Создание адреса
     * @param address модель адреса
     * @param contractorId id контрагента для привязки
     * @return созданная модель адреса
     */
    Address create(Address address, Integer contractorId);

    /** Изменение адреса
     * @param address модель адреса
     * @param contractorId id контрагента
     * @return измененная модель адреса
     */
    Address update(Address address, Integer contractorId);

    /** Удаление адреса
     * @param id адреса
     */
    void delete(Integer id);
}
