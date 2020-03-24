package com.zedapp.companyservice.service.implementation;

import com.zedapp.companyservice.dto.PurchaserDto;
import com.zedapp.companyservice.exception.ServiceConnectionProblemException;
import com.zedapp.companyservice.service.PurchaserService;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: implement methods
@Service
public class PurchaserServiceImpl implements PurchaserService {
    @Override
    public PurchaserDto getById(String id) throws ServiceConnectionProblemException {
        throw new ServiceConnectionProblemException("[ZEDAPP] Problem with connection to PurchaserService");
    }

    @Override
    public List<PurchaserDto> getByName(String name) {
        return null;
    }

    @Override
    public List<PurchaserDto> getByLastname(String lastname) {
        return null;
    }

    @Override
    public List<PurchaserDto> getByTelNumber(String telNumber) {
        return null;
    }
}
