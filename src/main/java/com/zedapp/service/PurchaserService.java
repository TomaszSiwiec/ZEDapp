package com.zedapp.service;

import com.zedapp.domain.Company;
import com.zedapp.domain.Order;
import com.zedapp.domain.Purchaser;
import com.zedapp.domain.dto.PurchaserDto;
import com.zedapp.mapper.PurchaserMapper;
import com.zedapp.repository.CompanyRepository;
import com.zedapp.repository.OrderRepository;
import com.zedapp.repository.PurchaserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PurchaserService {
    
    @Autowired
    private PurchaserRepository purchaserRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PurchaserMapper purchaserMapper;

    public List<PurchaserDto> getAll() {
        return purchaserMapper.mapToDtoList(purchaserRepository.findAll());
    }

    public PurchaserDto get(long id) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        return purchaserMapper.mapToDto(purchaser);
    }

    public PurchaserDto create(PurchaserDto purchaserDto) {
        Purchaser purchaser = new Purchaser();
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public PurchaserDto update(long id, PurchaserDto purchaserDto) {
        Purchaser purchaser = purchaserRepository.findOrThrow(id);
        purchaser.setName(purchaserDto.getName());
        purchaser.setLastname(purchaserDto.getLastname());
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public void delete(long id) {
        purchaserRepository.deleteById(id);
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
        return purchaserMapper.mapToDto(purchaserRepository.save(purchaser));
    }

    public List<PurchaserDto> getByCompanyId(Long companyId) {
        Company company = companyRepository.findOrThrow(companyId);
        List<Purchaser> purchasers = purchaserRepository.findAll();
        List<Purchaser> filteredPurchasers = purchasers.stream()
                .filter(purchaser -> purchaser.getCompanies().contains(company))
                .collect(Collectors.toList());
        return purchaserMapper.mapToDtoList(filteredPurchasers);
    }
}
