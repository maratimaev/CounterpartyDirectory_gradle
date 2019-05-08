package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.model.Person;
import ru.bellintegrator.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value ="/{sid}", produces = "application/json")
    public Person getPerson(@PathVariable String sid) {
        int id = Integer.parseInt(sid);
        return personService.getPerson(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        personService.delete(id);
    }
}
