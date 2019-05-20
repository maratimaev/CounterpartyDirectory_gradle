package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.view.PersonView;

@RestController
@RequestMapping("/api/person")
public class PersonController extends BaseController<PersonView> {
    @Autowired
    private BaseService<PersonView> personService;

    @Override
    BaseService<PersonView> getService() {
        return personService;
    }
}
