package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value ="/person/{sid}", produces = "application/json")
    @ResponseBody
    public Person getPerson(@PathVariable String sid) {
        int id = Integer.parseInt(sid);
        return personService.getPerson(id);
    }
}
