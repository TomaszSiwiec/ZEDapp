package com.zedapp.companyservice.mapper;

import com.zedapp.companyservice.domain.Company;
import com.zedapp.companyservice.dto.CompanyDto;
import com.zedapp.companyservice.dto.PurchaserDto;
import com.zedapp.companyservice.exception.ServiceConnectionProblemException;
import com.zedapp.companyservice.service.implementation.PurchaserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CompanyMapper {

    @Autowired
    private PurchaserServiceImpl purchaserService;

    public Company mapToCompany(CompanyDto companyDto) {
        List<String> purchaserIds = new ArrayList<>();
        if (companyDto.getPurchaserDtos() != null) {
            companyDto.getPurchaserDtos().stream()
                    .forEach(purchaserDto -> purchaserIds.add(purchaserDto.getId()));
        }

        Company company = new Company();
        company.setId(companyDto.getId());
        company.setOrderId(companyDto.getOrderId());
        company.setNip(companyDto.getNip());
        company.setName(companyDto.getName());
        company.setStreet(companyDto.getStreet());
        company.setBuildingNumber(companyDto.getBuildingNumber());
        company.setLocalNumber(companyDto.getLocalNumber());
        company.setZipCode(companyDto.getZipCode());
        company.setCity(companyDto.getCity());
        company.setCountry(companyDto.getCountry());
        company.setPurchasersIds(purchaserIds);

        return company;
    }

    public CompanyDto mapToCompanyDto(Company company) {
        List<PurchaserDto> purchaserDtos;
        try {
            purchaserDtos = purchaserService.getPurchasersByIds(company.getPurchasersIds());
        } catch (ServiceConnectionProblemException e) {
            log.error(e.getMessage());
            purchaserDtos = new ArrayList<>();
        }

        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setOrderId(company.getOrderId());
        companyDto.setNip(company.getNip());
        companyDto.setName(company.getName());
        companyDto.setStreet(company.getStreet());
        companyDto.setBuildingNumber(company.getBuildingNumber());
        companyDto.setLocalNumber(company.getLocalNumber());
        companyDto.setZipCode(company.getZipCode());
        companyDto.setCity(company.getCity());
        companyDto.setCountry(company.getCountry());
        companyDto.setPurchaserDtos(purchaserDtos);

        return companyDto;
    }

    public List<Company> mapToCompanyList(List<CompanyDto> companyDtoList) {
        List<Company> companies = companyDtoList.stream()
                .map(companyDto -> mapToCompany(companyDto))
                .collect(Collectors.toList());
        return Optional.ofNullable(companies).orElse(new ArrayList<>());
    }

    public List<CompanyDto> mapToCompanyDtoList(List<Company> companyList) {
        List<CompanyDto> companies = companyList.stream()
                .map(company -> mapToCompanyDto(company))
                .collect(Collectors.toList());
        return Optional.ofNullable(companies).orElse(new ArrayList<>());
    }
}
