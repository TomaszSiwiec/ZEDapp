package com.zedapp.mapper;

import com.zedapp.domain.Purchaser;
import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.PurchaserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaserMapper {
    public PurchaserDto mapToDto(Purchaser purchaser) {
        if (purchaser == null)
            return null;
        return new PurchaserDto(
                purchaser.getName(),
                purchaser.getLastname()
        );
    }

    public Purchaser mapToEntity(PurchaserDto purchaserDto) {
        if (purchaserDto == null)
            return null;
        return new Purchaser(
                0L,
                purchaserDto.getName(),
                purchaserDto.getLastname(),
                null,
                null
        );
    }

    public List<PurchaserDto> mapToDtoList(List<Purchaser> purchaserList) {
        if (purchaserList.isEmpty())
            return new ArrayList<>();
        if (purchaserList == null)
            return null;

        return purchaserList.stream()
                .map(purchaser -> mapToDto(purchaser))
                .collect(Collectors.toList());
    }

    public List<Purchaser> mapToEntityList(List<PurchaserDto> purchaserDtoList) {
        if (purchaserDtoList.isEmpty())
            return new ArrayList<>();
        if (purchaserDtoList == null)
            return null;

        return purchaserDtoList.stream()
                .map(purchaserDto -> mapToEntity(purchaserDto))
                .collect(Collectors.toList());
    }
}
