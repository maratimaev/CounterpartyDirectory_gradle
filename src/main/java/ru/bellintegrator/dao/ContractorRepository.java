package ru.bellintegrator.dao;

import ru.bellintegrator.model.Contractor;

/**
 * DAO контрагентов
 */
public interface ContractorRepository {

    /** Поиск контрагента по id
     * @param id контрагента
     * @return модель контрагента
     */
    Contractor findById(Integer id);

    /** Создание контрагента
     * @param contractor модель контрагента
     * @return созданная модель контрагента
     */
    Contractor create(Contractor contractor);

    /** Изменение контрагента
     * @param contractor модель контрагента
     * @return измененная модель контрагента
     */
    Contractor update(Contractor contractor);

    /** Удаление контаргента
     * @param id контрагента
     */
    void delete(Integer id);
}
