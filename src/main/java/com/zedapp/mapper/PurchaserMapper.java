package com.zedapp.mapper;

import com.zedapp.domain.Purchaser;
import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.PurchaserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaserMapper {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private OrderMapper orderMapper;

    public PurchaserDto mapToDto(Purchaser purchaser) {
        if (purchaser == null)
            return null;
        return new PurchaserDto(
                purchaser.getId(),
                purchaser.getName(),
                purchaser.getLastname(),
                companyMapper.mapToDtoList(purchaser.getCompanies()),
                orderMapper.mapToDtoList(purchaser.getOrders())
        );
    }

    public Purchaser mapToEntity(PurchaserDto purchaserDto) {
        if (purchaserDto == null)
            return null;
        return new Purchaser(
                purchaserDto.getId(),
                purchaserDto.getName(),
                purchaserDto.getLastname(),
                companyMapper.mapToEntityList(purchaserDto.getCompanyDtos()),
                orderMapper.mapToEntityList(purchaserDto.getOrderDtos())
        );
    }

    public List<PurchaserDto> mapToDtoList(List<Purchaser> purchaserList) {
        if (purchaserList == null)
            return null;

        return purchaserList.stream()
                .map(purchaser -> mapToDto(purchaser))
                .collect(Collectors.toList());
    }

    public List<Purchaser> mapToEntityList(List<PurchaserDto> purchaserDtoList) {
        if (purchaserDtoList == null)
            return null;

        return purchaserDtoList.stream()
                .map(purchaserDto -> mapToEntity(purchaserDto))
                .collect(Collectors.toList());
    }
}
