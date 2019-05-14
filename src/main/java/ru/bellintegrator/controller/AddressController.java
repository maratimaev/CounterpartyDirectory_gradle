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
import ru.bellintegrator.service.CrudService;
import ru.bellintegrator.view.AddressView;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private CrudService<AddressView> addressService;

    @GetMapping(value ="/{id}", produces = "application/json")
    @ResponseBody
    public AddressView getAddress(@PathVariable int id) {
        return addressService.get(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public AddressView create(@RequestBody AddressView addressView) {
        return addressService.create(addressView);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public AddressView update(@RequestBody AddressView addressView) {
        return addressService.update(addressView);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        addressService.delete(id);
    }
}
