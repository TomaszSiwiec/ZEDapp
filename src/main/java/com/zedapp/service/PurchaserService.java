package com.zedapp.service;

import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.PurchaserDto;
import com.zedapp.mapper.PurchaserMapper;
import com.zedapp.repository.PurchaserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PurchaserService {
    
    @Autowired
    private PurchaserRepository purchaserRepository;
    
    @Autowired
    private PurchaserMapper purchaserMapper;

    public List<PurchaserDto> getAll() {
        return purchaserMapper.mapToDtoList(purchaserRepository.findAll());
    }

    public PurchaserDto get(long id) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        return purchaserMapper.mapToDto(purchaser);
    }

    public PurchaserDto create(PurchaserDto purchaserDto) {
        Purchaser purchaser = new Purchaser();
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public PurchaserDto update(long id, PurchaserDto purchaserDto) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public void delete(long id) {
        purchaserRepository.deleteById(id);
    }
}
