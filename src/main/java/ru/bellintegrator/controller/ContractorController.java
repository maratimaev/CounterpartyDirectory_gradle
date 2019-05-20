package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.view.ContractorView;

@RestController
@RequestMapping("/api/contractor")
public class ContractorController extends BaseController<ContractorView> {
    @Autowired
    private BaseService<ContractorView> contractorService;

    @Override
    BaseService<ContractorView> getService() {
        return contractorService;
    }
}
