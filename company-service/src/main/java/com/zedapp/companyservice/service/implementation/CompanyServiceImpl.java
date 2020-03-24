package com.zedapp.companyservice.service.implementation;

import com.zedapp.companyservice.domain.Company;
import com.zedapp.companyservice.dto.CompanyDto;
import com.zedapp.companyservice.mapper.CompanyMapper;
import com.zedapp.companyservice.repository.CompanyRepository;
import com.zedapp.companyservice.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public ResponseEntity create(CompanyDto companyDto) {
        if (findDuplicationCompanyWithNip(companyDto.getNip())) {
            String errorMessage = "[ZEDAPP] Company with NIP = " + companyDto.getNip() + " already exists!";
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }

        Company company = companyMapper.mapToCompany(companyDto);
        company.setOrderId(getNextAvailableOrderId());
        companyRepository.save(company);
        return new ResponseEntity(company, HttpStatus.OK);
    }

    private boolean findDuplicationCompanyWithNip(String nip) {
        for (Company company : companyRepository.findAll()) {
            if (nip.equals(company.getNip())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ResponseEntity update(CompanyDto companyDto) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(String id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteAll() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAll() {
        List<CompanyDto> companies = companyMapper.mapToCompanyDtoList(companyRepository.findAll());
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getById(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) {
            String errorMessage = "[ZEDAPP] Company with id = " + id + " not found!";
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorMessage);
        }
        return new ResponseEntity(companyMapper.mapToCompanyDto(company.get()), HttpStatus.OK);
    }

    @Override
    public CompanyDto findByNip(String nip) {
        return null;
    }

    @Override
    public List<CompanyDto> findByName(String name) {
        return null;
    }

    @Override
    public List<CompanyDto> findByStreet(String street) {
        return null;
    }

    @Override
    public List<CompanyDto> findByZipCode(String zipCode) {
        return null;
    }

    @Override
    public List<CompanyDto> findByCity(String city) {
        return null;
    }

    private Long getNextAvailableOrderId() {
        List<Company> companies = getCompaniesWithExistingOrderIdField();
        if (companies.isEmpty()) {
            return 1L;
        }
        Company lastAdded = Collections.max(companies, Comparator.comparingLong(company -> company.getOrderId()));
        Long max = lastAdded.getOrderId() + 1;
        return max;
    }

    private List<Company> getCompaniesWithExistingOrderIdField() {
        List<Company> companies = companyRepository.findAll();
        List<Company> filteredCompanies = new ArrayList<>();

        for (Company company : companies) {
            if (company.getOrderId() != null) {
                filteredCompanies.add(company);
            }
        }

        return filteredCompanies;
    }
}
