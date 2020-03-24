package com.zedapp.companyservice.service;

import com.zedapp.companyservice.dto.CompanyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {

    ResponseEntity create(CompanyDto companyDto);

    ResponseEntity update(String id, CompanyDto companyDto);

    ResponseEntity delete(String id);

    ResponseEntity deleteAll();

    ResponseEntity<List<CompanyDto>> getAll();

    ResponseEntity<CompanyDto> getById(String id);

    CompanyDto findByNip(String nip);

    List<CompanyDto> findByName(String name);

    List<CompanyDto> findByStreet(String street);

    List<CompanyDto> findByZipCode(String zipCode);

    List<CompanyDto> findByCity(String city);

}
