package com.zedapp.purchaserservice.service.implementation;

import com.zedapp.purchaserservice.dto.PurchaserDto;
import com.zedapp.purchaserservice.service.PurchaserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaserServiceImpl implements PurchaserService {
    @Override
    public ResponseEntity<List<PurchaserDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<PurchaserDto> getById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<PurchaserDto>> getByName(String name) {
        return null;
    }

    @Override
    public ResponseEntity<List<PurchaserDto>> getByLastName(String lastName) {
        return null;
    }

    @Override
    public ResponseEntity<List<PurchaserDto>> getByTelNumber(String telNumber) {
        return null;
    }

    @Override
    public ResponseEntity<PurchaserDto> create(PurchaserDto purchaserDto) {
        return null;
    }

    @Override
    public ResponseEntity<PurchaserDto> update(String id, PurchaserDto purchaserDto) {
        return null;
    }

    @Override
    public ResponseEntity deleteById(String id) {
        return null;
    }

    @Override
    public ResponseEntity deleteAll() {
        return null;
    }
}
