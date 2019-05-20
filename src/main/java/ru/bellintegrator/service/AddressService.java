package ru.bellintegrator.service;

import ru.bellintegrator.view.AddressView;

import java.util.List;

public interface AddressService extends BaseService<AddressView>{
    AddressView getByTypeAndContractorId(int addressType, int contractorId);
    List<AddressView> getByContractorId(int contractorId);
}
