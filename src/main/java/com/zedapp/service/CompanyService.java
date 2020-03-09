package com.zedapp.service;

import com.zedapp.domain.Company;
import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.CompanyDto;
import com.zedapp.mapper.CompanyMapper;
import com.zedapp.repository.CompanyRepository;
import com.zedapp.repository.PurchaserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PurchaserRepository purchaserRepository;

    @Autowired
    private CompanyMapper companyMapper;

    public List<CompanyDto> getAll() {
        return companyMapper.mapToDtoList(companyRepository.findAll());
    }

    public CompanyDto get(long id) {
        Company company = companyRepository.findOrThrow(id);
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
        return companyMapper.mapToDto(companyRepository.save(company));
    }

    public void delete(long id) {
        companyRepository.deleteById(id);
    }

    public List<CompanyDto> getByPurchaserId(Long purchaserId) {
        Purchaser purchaser = purchaserRepository.findOrThrow(purchaserId);
        return companyMapper.mapToDtoList(purchaser.getCompanies());
    }
}
