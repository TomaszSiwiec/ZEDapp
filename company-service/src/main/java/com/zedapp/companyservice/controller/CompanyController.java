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

    @PostMapping(value = "/create")
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto companyDto) {
        return companyService.create(companyDto);
    }

    @PutMapping(value = "/update/{companyId}")
    public ResponseEntity<CompanyDto> update(@PathVariable("companyId") String id, CompanyDto companyDto) {
        return companyService.update(id, companyDto);
    }
}
