package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.ContractorService;
import ru.bellintegrator.view.ContractorView;

@RestController
@RequestMapping("/api/contractor")
public class ContractorController {
    @Autowired
    private ContractorService contractorService;

    @GetMapping(value ="/{id}", produces = "application/json")
    @ResponseBody
    public ContractorView getContractor(@PathVariable int id) {
        return contractorService.getById(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public ContractorView create(@RequestBody ContractorView contractorView) {
        return contractorService.create(contractorView);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ContractorView update(@RequestBody ContractorView contractorView) {
        return contractorService.update(contractorView);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        contractorService.delete(id);
    }
}
