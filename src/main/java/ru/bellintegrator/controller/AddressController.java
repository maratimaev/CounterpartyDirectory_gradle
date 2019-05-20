package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.view.AddressView;

@RestController
@RequestMapping("/api/address")
public class AddressController extends BaseController<AddressView> {
    @Autowired
    private BaseService<AddressView> addressService;

    @Override
    BaseService<AddressView> getService() {
        return addressService;
    }
}
