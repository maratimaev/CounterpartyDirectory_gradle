package ru.bellintegrator.service;

import ru.bellintegrator.view.AddressView;

import java.util.List;

/**
 * Сервис адресов
 */
public interface AddressService extends BaseService<AddressView>{
    /** Поиск адресов по типу и id контаргента
     * @param addressType тип адреса
     * @param contractorId id контрагента
     * @return отображение адреса
     */
    AddressView getByTypeAndContractorId(int addressType, int contractorId);

    /** Поиск адресов по id контрагента
     * @param contractorId id контрагента
     * @return список отображений адресов
     */
    List<AddressView> getByContractorId(int contractorId);
}
