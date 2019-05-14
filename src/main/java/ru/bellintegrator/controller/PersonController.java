package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.PersonService;
import ru.bellintegrator.view.PersonView;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value ="/{sid}", produces = "application/json")
    public PersonView getPerson(@PathVariable String sid) {
        int id = Integer.parseInt(sid);
        return personService.getPerson(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public PersonView create(@RequestBody PersonView personView, @RequestParam int contractorId) {
        return personService.create(personView, contractorId);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public PersonView update(@RequestBody PersonView personView, @RequestParam int contractorId) {
        return personService.update(personView, contractorId);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id, @RequestParam int contractorId) {
        personService.delete(id, contractorId);
    }
}
