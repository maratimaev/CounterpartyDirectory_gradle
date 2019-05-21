package ru.bellintegrator.service;

import ru.bellintegrator.view.PersonView;

import java.util.List;

/**
 * Сервис контактов
 */
public interface PersonService extends BaseService<PersonView>{
    /** Поиск контактов по id контрагента
     * @param contractorId id контрагента
     * @return список отображений контактов
     */
    List<PersonView> getByContractorId(int contractorId);
}
