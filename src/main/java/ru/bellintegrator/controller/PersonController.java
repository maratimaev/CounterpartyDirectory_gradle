package ru.bellintegrator.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.view.PersonView;

/**
 * Контроллер контактов
 */
@RestController
@RequestMapping("/api/person")
@Api(tags = {"Person"})
public class PersonController extends BaseController<PersonView> {
    /**
     * Сервис контактов
     */
    @Autowired
    private BaseService<PersonView> personService;

    /** Геттер сервиса
     * @return сервис
     */
    @Override
    BaseService<PersonView> getService() {
        return personService;
    }
}
