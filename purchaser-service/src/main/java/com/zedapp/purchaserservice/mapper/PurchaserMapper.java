package com.zedapp.purchaserservice.mapper;

import com.zedapp.purchaserservice.domain.Purchaser;
import com.zedapp.purchaserservice.dto.PurchaserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PurchaserMapper {

    public Purchaser mapToPurchaser(PurchaserDto purchaserDto) {
        Purchaser purchaser = new Purchaser();
        purchaser.setId(purchaserDto.getId());
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        purchaser.setTelNumber(purchaserDto.getTelNumber());
        return purchaser;
    }

    public PurchaserDto mapToPurchaserDto(Purchaser purchaser) {
        PurchaserDto purchaserDto = new PurchaserDto();
        purchaserDto.setId(purchaser.getId());
        purchaserDto.setName(purchaser.getName());
        purchaserDto.setLastname(purchaser.getLastname());
        purchaserDto.setTelNumber(purchaser.getTelNumber());
        return purchaserDto;
    }

    public List<Purchaser> mapToPurchaserList(List<PurchaserDto> purchaserDtoList) {
        List<Purchaser> purchasers = purchaserDtoList.stream()
                .map(purchaserDto -> mapToPurchaser(purchaserDto))
                .collect(Collectors.toList());
        return Optional.ofNullable(purchasers).orElse(new ArrayList<>());
    }

    public List<PurchaserDto> mapToPurchaserDtoList(List<Purchaser> purchaserList) {
        List<PurchaserDto> purchasers = purchaserList.stream()
                .map(purchaser -> mapToPurchaserDto(purchaser))
                .collect(Collectors.toList());
        return Optional.ofNullable(purchasers).orElse(new ArrayList<>());
    }
}
