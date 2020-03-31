package com.zedapp.purchaserservice.service.implementation;

import com.zedapp.purchaserservice.domain.Purchaser;
import com.zedapp.purchaserservice.dto.PurchaserDto;
import com.zedapp.purchaserservice.exception.ObjectNotFoundException;
import com.zedapp.purchaserservice.mapper.PurchaserMapper;
import com.zedapp.purchaserservice.repository.PurchaserRepository;
import com.zedapp.purchaserservice.service.PurchaserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class PurchaserServiceImpl implements PurchaserService {

    @Autowired
    private PurchaserRepository purchaserRepository;

    @Autowired
    private PurchaserMapper purchaserMapper;

    @Override
    public ResponseEntity<List<PurchaserDto>> getAll() {
        List<PurchaserDto> purchasers = purchaserMapper.mapToPurchaserDtoList(purchaserRepository.findAll());
        return new ResponseEntity<>(purchasers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaserDto> getById(String id) {
        try {
            Purchaser purchaser = purchaserRepository.findOrThrow(id);
            return new ResponseEntity<>(purchaserMapper.mapToPurchaserDto(purchaser), HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<PurchaserDto>> getByName(String name) {
        List<Purchaser> purchasers = purchaserRepository.findByName(name);
        return new ResponseEntity<>(purchaserMapper.mapToPurchaserDtoList(purchasers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PurchaserDto>> getByLastName(String lastName) {
        List<Purchaser> purchasers = purchaserRepository.findByLastname(lastName);
        return new ResponseEntity<>(purchaserMapper.mapToPurchaserDtoList(purchasers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PurchaserDto>> getByTelNumber(String telNumber) {
        List<Purchaser> purchasers = purchaserRepository.findByTelNumber(telNumber);
        return new ResponseEntity<>(purchaserMapper.mapToPurchaserDtoList(purchasers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaserDto> create(PurchaserDto purchaserDto) {
        Purchaser purchaser = new Purchaser();
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        purchaser.setTelNumber(purchaserDto.getTelNumber());
        purchaserRepository.save(purchaser);
        return new ResponseEntity<>(purchaserMapper.mapToPurchaserDto(purchaser), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaserDto> update(String id, PurchaserDto purchaserDto) {
        try {
            Purchaser purchaser = purchaserRepository.findOrThrow(id);
            purchaser.setName(purchaserDto.getName());
            purchaser.setLastname(purchaserDto.getLastname());
            purchaser.setTelNumber(purchaserDto.getTelNumber());
            purchaserRepository.save(purchaser);
            return new ResponseEntity<>(purchaserMapper.mapToPurchaserDto(purchaser), HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteById(String id) {
        try {
            Purchaser purchaser = purchaserRepository.findOrThrow(id);
            purchaserRepository.deleteById(purchaser.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteAll() {
        purchaserRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
