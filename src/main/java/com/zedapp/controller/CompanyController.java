package com.zedapp.controller;

import com.zedapp.domain.dto.CompanyDto;
import com.zedapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/getAll")
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/get")
    public CompanyDto getById(@RequestParam(value = "id") Long id) {
        return companyService.get(id);
    }

    @GetMapping("/getAllByPurchaserId")
    public List<CompanyDto> getByPurchaserId(@RequestParam(value = "purchaserId") Long purchaserId) {
        return companyService.getByPurchaserId(purchaserId);
    }

    @PostMapping("/create")
    public CompanyDto create(@RequestBody CompanyDto companyDto) {
        return companyService.create(companyDto);
    }

    @PutMapping("/update")
    public CompanyDto update(@RequestParam(value = "id") Long id, @RequestBody CompanyDto companyDto) {
        return companyService.update(id, companyDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        companyService.delete(id);
    }
}
