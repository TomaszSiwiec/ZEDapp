package com.zedapp.mapper;

import com.zedapp.domain.Company;
import com.zedapp.domain.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    @Autowired
    private PurchaserMapper purchaserMapper;

    public CompanyDto mapToDto(Company company) {
        if (company == null)
            return null;
        return new CompanyDto(
                company.getId(),
                company.getNip(),
                company.getName(),
                company.getStreet(),
                company.getBuildingNumber(),
                company.getLocalNumber(),
                company.getZipCode(),
                company.getCity(),
                company.getCountry(),
                purchaserMapper.mapToDtoList(company.getPurchasers())
        );
    }

    public Company mapToEntity(CompanyDto companyDto) {
        if (companyDto == null)
            return null;
        return new Company(
                companyDto.getId(),
                companyDto.getNip(),
                companyDto.getName(),
                companyDto.getStreet(),
                companyDto.getBuildingNumber(),
                companyDto.getLocalNumber(),
                companyDto.getZipCode(),
                companyDto.getCity(),
                companyDto.getCountry(),
                purchaserMapper.mapToEntityList(companyDto.getPurchaserDtos())
        );
    }

    public List<CompanyDto> mapToDtoList(List<Company> companyList) {
        if (companyList.isEmpty())
            return new ArrayList<>();
        if (companyList == null)
            return null;

        return companyList.stream()
                .map(company -> mapToDto(company))
                .collect(Collectors.toList());
    }

    public List<Company> mapToEntityList(List<CompanyDto> companyDtoList) {
        if (companyDtoList.isEmpty())
            return new ArrayList<>();
        if (companyDtoList == null)
            return null;

        return companyDtoList.stream()
                .map(companyDto -> mapToEntity(companyDto))
                .collect(Collectors.toList());
    }
}
