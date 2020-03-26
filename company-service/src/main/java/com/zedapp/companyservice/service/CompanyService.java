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

    ResponseEntity<CompanyDto> getByNip(String nip);

    ResponseEntity<List<CompanyDto>> getByName(String name);

    ResponseEntity<List<CompanyDto>> getByZipCode(String zipCode);

    ResponseEntity<List<CompanyDto>> getByCity(String city);

}
