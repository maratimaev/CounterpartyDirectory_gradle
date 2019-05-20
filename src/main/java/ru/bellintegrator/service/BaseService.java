package ru.bellintegrator.service;

import ru.bellintegrator.view.BaseView;

/** Базовые методы сервисов
 * @param <T> возвращаемое предстваление
 */
public interface BaseService<T extends BaseView> {
    /** Получение объекта из базы по id
     * @param id объекта
     * @return объект
     */
    T getById(int id);

    /** Создание объекта в базе
     * @param t объект
     * @param contractorId id контрагента для привязки объектов
     * @return созданный объект
     */
    T create(T t, int contractorId);

    /** Модификация объекта
     * @param t объект
     * @param contractorId id контрагента с привязанными объектами
     * @return модифицированный объект
     */
    T update(T t, int contractorId);

    /** Удаление объекта
     * @param id объекта
     * @param contractorId id контрагента
     */
    void delete(int id, int contractorId);
}
