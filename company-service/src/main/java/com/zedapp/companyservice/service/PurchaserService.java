package com.zedapp.companyservice.service;

import com.zedapp.companyservice.dto.PurchaserDto;
import com.zedapp.companyservice.exception.ServiceConnectionProblemException;

import java.util.List;

public interface PurchaserService {
    List<PurchaserDto> getAll() throws ServiceConnectionProblemException;
    PurchaserDto getById(String id) throws ServiceConnectionProblemException;
    List<PurchaserDto> getByName(String name) throws ServiceConnectionProblemException;
    List<PurchaserDto> getByLastname(String lastname) throws ServiceConnectionProblemException;
    List<PurchaserDto> getByTelNumber(String telNumber) throws ServiceConnectionProblemException;
    void createIfNotExist(List<PurchaserDto> purchaserDtos) throws ServiceConnectionProblemException;
    PurchaserDto createPurchaser(PurchaserDto purchaserDto) throws ServiceConnectionProblemException;
}
