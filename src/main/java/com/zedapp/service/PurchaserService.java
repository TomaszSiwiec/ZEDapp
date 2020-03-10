package com.zedapp.service;

import com.zedapp.domain.Company;
import com.zedapp.domain.Order;
import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.PurchaserDto;
import com.zedapp.mapper.PurchaserMapper;
import com.zedapp.repository.CompanyRepository;
import com.zedapp.repository.OrderRepository;
import com.zedapp.repository.PurchaserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class PurchaserService {
    
    @Autowired
    private PurchaserRepository purchaserRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PurchaserMapper purchaserMapper;

    public List<PurchaserDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity PURCHASERS");
        return purchaserMapper.mapToDtoList(purchaserRepository.findAll());
    }

    public PurchaserDto get(long id) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity PURCHASERS");
        return purchaserMapper.mapToDto(purchaser);
    }

    public PurchaserDto create(PurchaserDto purchaserDto) {
        Purchaser purchaser = new Purchaser();
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        log.info("[ZEDAPP] Added new object with name: " + purchaser.getName() + " " + purchaser.getLastname() + " to entity PURCHASERS");
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public PurchaserDto update(long id, PurchaserDto purchaserDto) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity PURCHASERS");
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public void delete(long id) {
        purchaserRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity PURCHASERS");
    }

    public PurchaserDto assignCompany(Long purchaserId, Long companyId) {
        Purchaser purchaser = purchaserRepository.findOrThrow(purchaserId);
        Company company = companyRepository.findOrThrow(companyId);
        List<Company> companies = purchaser.getCompanies();
        companies.add(company);
        purchaser.setCompanies(companies);

        List<Purchaser> companysPurchasers = company.getPurchasers();
        companysPurchasers.add(purchaser);
        company.setPurchasers(companysPurchasers);
        companyRepository.save(company);
        log.info("[ZEDAPP] Assigned purchaser with id: " + purchaserId + " to company with id: " + companyId);
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public List<PurchaserDto> getByCompanyId(Long companyId) {
        Company company = companyRepository.findOrThrow(companyId);
        List<Purchaser> purchasers = purchaserRepository.findAll();
        List<Purchaser> filteredPurchasers = purchasers.stream()
                .filter(purchaser -> purchaser.getCompanies().contains(company))
                .collect(Collectors.toList());
        log.info("[ZEDAPP] Returned list of Purchaser objects with company's id: " + companyId);
        return purchaserMapper.mapToDtoList(filteredPurchasers);
    }
}
