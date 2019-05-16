package ru.bellintegrator.service;

import ru.bellintegrator.view.AddressView;

import java.util.List;

public interface AddressService {
    AddressView getById(int id);
    AddressView getByTypeAndContractorId(int addressType, int contractorId);
    List<AddressView> getByContractorId(int contractorId);
    AddressView create(AddressView addressView, int contractorId);
    AddressView update(AddressView addressView, int contractorId);
    void delete(int id);
}
