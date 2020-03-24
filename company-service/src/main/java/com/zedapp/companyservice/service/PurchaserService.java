package com.zedapp.companyservice.service;

import com.zedapp.companyservice.dto.PurchaserDto;

import java.util.List;

public interface PurchaserService {
    PurchaserDto getById(String id);
    List<PurchaserDto> getByName(String name);
    List<PurchaserDto> getByLastname(String lastname);
    List<PurchaserDto> getByTelNumber(String telNumber);
}
