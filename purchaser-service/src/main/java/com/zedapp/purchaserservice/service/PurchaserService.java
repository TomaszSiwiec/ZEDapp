package com.zedapp.purchaserservice.service;

import com.zedapp.purchaserservice.dto.PurchaserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaserService {
    ResponseEntity<List<PurchaserDto>> getAll();
    ResponseEntity<PurchaserDto> getById(String id);
    ResponseEntity<List<PurchaserDto>> getByName(String name);
    ResponseEntity<List<PurchaserDto>> getByLastName(String lastName);
    ResponseEntity<List<PurchaserDto>> getByTelNumber(String telNumber);
    ResponseEntity<PurchaserDto> create(PurchaserDto purchaserDto);
    ResponseEntity<PurchaserDto> update(String id, PurchaserDto purchaserDto);
    ResponseEntity deleteById(String id);
    ResponseEntity deleteAll();
}
