package com.zedapp.companyservice.service.implementation;

import com.zedapp.companyservice.config.URIConfig;
import com.zedapp.companyservice.domain.Company;
import com.zedapp.companyservice.dto.CompanyDto;
import com.zedapp.companyservice.dto.PurchaserDto;
import com.zedapp.companyservice.exception.ObjectNotFoundException;
import com.zedapp.companyservice.exception.ServiceConnectionProblemException;
import com.zedapp.companyservice.mapper.CompanyMapper;
import com.zedapp.companyservice.repository.CompanyRepository;
import com.zedapp.companyservice.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private PurchaserServiceImpl purchaserService;

    @Override
    public ResponseEntity create(CompanyDto companyDto) {
        if (!checkPurchaserServiceAvailable()) {
            String errorMessage = "[ZEDAPP] You are not able to create Company with name = " + companyDto.getName() +
                    " due to Purchaser Service connection problem";
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

        if (findDuplicationCompanyWithNip(companyDto.getNip())) {
            String errorMessage = "[ZEDAPP] Company with NIP = " + companyDto.getNip() + " already exists!";
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }

        try {
            List<String> purchaserIds = companyDto.getPurchaserDtos().stream()
                    .map(purchaserDto -> purchaserDto.getId())
                    .collect(Collectors.toList());

            purchaserService.createIfNotExist(companyDto.getPurchaserDtos());

            Company company = companyMapper.mapToCompany(companyDto);
            company.setOrderId(getNextAvailableOrderId());
            company.setPurchasersIds(purchaserIds);
            companyRepository.save(company);

            return new ResponseEntity(company, HttpStatus.OK);
        } catch (ServiceConnectionProblemException ex) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ex.getMessage());
        }
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
    public ResponseEntity update(String id, CompanyDto companyDto) {
        if (!checkPurchaserServiceAvailable()) {
            String errorMessage = "[ZEDAPP] You are not able to edit Company with id = " + id +
                    " due to Purchaser Service connection problem";
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

        try {
            Company company = companyRepository.findOrThrow(id);
            company.setName(companyDto.getName());
            company.setStreet(companyDto.getStreet());
            company.setBuildingNumber(companyDto.getBuildingNumber());
            company.setLocalNumber(companyDto.getLocalNumber());
            company.setZipCode(companyDto.getZipCode());
            company.setCity(companyDto.getCity());
            company.setCountry(companyDto.getCountry());
            company.setNip(companyDto.getNip());

            List<String> purchaserIds = new ArrayList<>();
            for (PurchaserDto purchaserDto : companyDto.getPurchaserDtos()) {
                purchaserIds.add(purchaserDto.getId());
            }
            company.setPurchasersIds(purchaserIds);
            companyRepository.save(company);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            String errorMessage = "[ZEDAPP] Object with id = " + id + " not found!";
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity delete(String id) {
        try {
            Company company = companyRepository.findOrThrow(id);
            companyRepository.delete(company);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            String errorMessage = "[ZEDAPP] Object with id = " + id + " not found!";
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity deleteAll() {
        companyRepository.deleteAll();
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

    public boolean checkPurchaserServiceAvailable() {

        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.fromHttpUrl(URIConfig.PURCHASER_SERVICE_URL + "/checkStatus")
                .build().encode().toUri();
        ResponseEntity<String> response = restTemplate.getForObject(uri, ResponseEntity.class);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody().equals("ALIVE")) {
            return true;
        }
        return false;
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
