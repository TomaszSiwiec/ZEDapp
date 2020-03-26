package com.zedapp.companyservice.controller;

import com.zedapp.companyservice.dto.CompanyDto;
import com.zedapp.companyservice.service.implementation.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto companyDto) {
        return companyService.create(companyDto);
    }

    @PutMapping(value = "/update/{companyId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> update(@PathVariable("companyId") String id, CompanyDto companyDto) {
        return companyService.update(id, companyDto);
    }

    @DeleteMapping(value = "/delete/{companyId}")
    public ResponseEntity deleteById(@PathVariable("companyId") String id) {
        return companyService.delete(id);
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity deleteAll() {
        return companyService.deleteAll();
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDto>> getAll() {
        return companyService.getAll();
    }

    @GetMapping(value = "/get/{companyId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> getById(@PathVariable("companyId") String id) {
        return companyService.getById(id);
    }

    @GetMapping(value = "/getByNip/{nip}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> getByNip(@PathVariable("nip") String nip) {
        return companyService.getByNip(nip);
    }

    @GetMapping(value = "/getByName/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDto>> getByName(@PathVariable("name") String name) {
        return companyService.getByName(name);
    }

    @GetMapping(value = "/getByZipCode/{zipCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDto>> getByZipCode(@PathVariable("zipCode") String zipCode) {
        return companyService.getByZipCode(zipCode);
    }

    @GetMapping(value = "/getByCity/{cityName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDto>> getByCityName(@PathVariable("cityName") String cityName) {
        return companyService.getByCity(cityName);
    }
}
