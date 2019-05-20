package ru.bellintegrator.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.view.BaseView;

/** Базовый абстрактный контроллер
 * @param <T> оперируемые объекты
 */
@RestController
public abstract class BaseController<T extends BaseView> {

    /** Получение конкретного сервиса для работы с объектами
     * @return бин сервиса
     */
    abstract BaseService<T> getService();

    /** Получение объекта по id
     * @param id объекта
     * @return представление объекта
     */
    @GetMapping(value ="/{id}", produces = "application/json")
    @ResponseBody
    public T get(@PathVariable int id) {
        return getService().getById(id);
    }

    /** Создание объекта
     * @param t json
     * @return представление объекта
     */
    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public T create(@RequestBody T t) {
        return getService().create(t, t.getContractorId());
    }

    /** Модификация объекта
     * @param t json
     * @return представление объекта
     */
    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public T update(@RequestBody T t) {
        return getService().update(t, t.getContractorId());
    }

    /** Удаление объекта
     * @param id объекта
     * @param contractorId id контрагента
     */
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id, @RequestParam int contractorId) {
        getService().delete(id, contractorId);
    }
}
