package com.zedapp.service;

import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.PurchaserDto;
import com.zedapp.mapper.PurchaserMapper;
import com.zedapp.repository.PurchaserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class PurchaserService {
    
    @Autowired
    private PurchaserRepository purchaserRepository;
    
    @Autowired
    private PurchaserMapper purchaserMapper;

    public List<PurchaserDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity PURCHASERS");
        return purchaserMapper.mapToDtoList(purchaserRepository.findAll());
    }

    public PurchaserDto get(long id) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity PURCHASERS");
        return purchaserMapper.mapToDto(purchaser);
    }

    public PurchaserDto create(PurchaserDto purchaserDto) {
        Purchaser purchaser = new Purchaser();
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        log.info("[ZEDAPP] Added new object with name: " + purchaser.getName() + " " + purchaser.getLastname() + " to entity PURCHASERS");
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public PurchaserDto update(long id, PurchaserDto purchaserDto) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity PURCHASERS");
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public void delete(long id) {
        purchaserRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity PURCHASERS");
    }
}
