package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.model.Contractor;
import ru.bellintegrator.service.ContractorService;

@RestController
@RequestMapping("/api")
public class ContractorController {
    @Autowired
    private ContractorService contractorService;

    @GetMapping(value ="/contractor/{sid}", produces = "application/json")
    @ResponseBody
    public Contractor getContractor(@PathVariable String sid) {
        int id = Integer.parseInt(sid);
        return contractorService.getContractor(id);
    }
}
