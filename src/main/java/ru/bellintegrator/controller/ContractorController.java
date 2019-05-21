package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.service.BaseService;
import ru.bellintegrator.view.ContractorView;

/**
 * Контроллер контрагентов
 */
@RestController
@RequestMapping("/api/contractor")
public class ContractorController extends BaseController<ContractorView> {
    /**
     * Сервис контрагентов
     */
    @Autowired
    private BaseService<ContractorView> contractorService;

    /**Геттер сервиса
     * @return сервис
     */
    @Override
    BaseService<ContractorView> getService() {
        return contractorService;
    }
}
