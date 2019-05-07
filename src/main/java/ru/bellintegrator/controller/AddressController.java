package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.model.Address;
import ru.bellintegrator.service.AddressService;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping(value ="/address/{sid}", produces = "application/json")
    @ResponseBody
    public Address getAddress(@PathVariable String sid) {
        int id = Integer.parseInt(sid);
        return addressService.getAddress(id);
    }
}
