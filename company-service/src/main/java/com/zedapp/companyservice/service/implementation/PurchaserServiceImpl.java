package com.zedapp.companyservice.service.implementation;

import com.zedapp.companyservice.dto.PurchaserDto;
import com.zedapp.companyservice.exception.ServiceConnectionProblemException;
import com.zedapp.companyservice.service.PurchaserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//TODO: implement methods
@Slf4j
@Service
public class PurchaserServiceImpl implements PurchaserService {

    @Override
    public List<PurchaserDto> getAll() throws ServiceConnectionProblemException {
        throw new ServiceConnectionProblemException("[ZEDAPP] Problem with connection to PurchaserService");
    }

    @Override
    public PurchaserDto getById(String id) throws ServiceConnectionProblemException {
        throw new ServiceConnectionProblemException("[ZEDAPP] Problem with connection to PurchaserService");
    }

    @Override
    public List<PurchaserDto> getByName(String name) throws ServiceConnectionProblemException {
        throw new ServiceConnectionProblemException("[ZEDAPP] Problem with connection to PurchaserService");
    }

    @Override
    public List<PurchaserDto> getByLastname(String lastname) throws ServiceConnectionProblemException {
        throw new ServiceConnectionProblemException("[ZEDAPP] Problem with connection to PurchaserService");
    }

    @Override
    public List<PurchaserDto> getByTelNumber(String telNumber) throws ServiceConnectionProblemException {
        throw new ServiceConnectionProblemException("[ZEDAPP] Problem with connection to PurchaserService");
    }

    @Override
    public void createIfNotExist(List<PurchaserDto> purchaserDtos) throws ServiceConnectionProblemException {
        List<PurchaserDto> purchasersToCreate = new ArrayList<>();
        for (PurchaserDto purchaserDto : purchaserDtos) {
            if (getById(purchaserDto.getId()) == null) {
                purchasersToCreate.add(purchaserDto);
            }
        }

        purchasersToCreate.stream()
                .forEach(purchaserDto -> {
                    try {
                        createPurchaser(purchaserDto);
                    } catch (ServiceConnectionProblemException e) {
                        log.error(e.getMessage());
                    }
                });
    }

    @Override
    public PurchaserDto createPurchaser(PurchaserDto purchaserDto) throws ServiceConnectionProblemException {
        return null;
    }

    @Override
    public List<PurchaserDto> getPurchasersByIds(List<String> ids) throws ServiceConnectionProblemException {
        return new ArrayList<>();
    }
}
