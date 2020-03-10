package com.zedapp.service;

import com.zedapp.domain.Company;
import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.CompanyDto;
import com.zedapp.mapper.CompanyMapper;
import com.zedapp.repository.CompanyRepository;
import com.zedapp.repository.PurchaserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PurchaserRepository purchaserRepository;

    @Autowired
    private CompanyMapper companyMapper;

    public List<CompanyDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity COMPANIES");
        return companyMapper.mapToDtoList(companyRepository.findAll());
    }

    public CompanyDto get(long id) {
        Company company = companyRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity COMPANIES");
        return companyMapper.mapToDto(company);
    }

    public CompanyDto create(CompanyDto companyDto) {
        Company company = new Company();
        company.setNip(companyDto.getNip());
        company.setName(companyDto.getName());
        company.setStreet(companyDto.getStreet());
        company.setBuildingNumber(companyDto.getBuildingNumber());
        company.setLocalNumber(companyDto.getLocalNumber());
        company.setZipCode(companyDto.getZipCode());
        company.setCity(companyDto.getCity());
        company.setCountry(companyDto.getCountry());
        log.info("[ZEDAPP] Added new object with name: " + company.getName() + " to entity COMPANIES");
        return companyMapper.mapToDto(companyRepository.save(company));
    }

    public CompanyDto update(long id, CompanyDto companyDto) {
        Company company = companyRepository.findOrThrow(id);
        company.setNip(companyDto.getNip());
        company.setName(companyDto.getName());
        company.setStreet(companyDto.getStreet());
        company.setBuildingNumber(companyDto.getBuildingNumber());
        company.setLocalNumber(companyDto.getLocalNumber());
        company.setZipCode(companyDto.getZipCode());
        company.setCity(companyDto.getCity());
        company.setCountry(companyDto.getCountry());
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity COMPANIES");
        return companyMapper.mapToDto(companyRepository.save(company));
    }

    public void delete(long id) {
        companyRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity COMPANIES");
    }

    public List<CompanyDto> getByPurchaserId(Long purchaserId) {
        Purchaser purchaser = purchaserRepository.findOrThrow(purchaserId);
        log.info("[ZEDAPP] Returned all objects with Purchaser ID: " + purchaserId + " from entity COMPANIES");
        return companyMapper.mapToDtoList(purchaser.getCompanies());
    }
}
