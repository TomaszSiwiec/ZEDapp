package com.zedapp.controller;

import com.zedapp.domain.dto.PurchaserDto;
import com.zedapp.service.PurchaserService;
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
@RequestMapping("/zedapp/purchaser")
public class PurchaserController {
    @Autowired
    private PurchaserService purchaserService;

    @GetMapping("/getAll")
    public List<PurchaserDto> getAll() {
        return purchaserService.getAll();
    }

    @GetMapping("/get")
    public PurchaserDto getById(@RequestParam(value = "id") Long id) {
        return purchaserService.get(id);
    }

    @PostMapping("/create")
    public PurchaserDto create(@RequestBody PurchaserDto purchaserDto) {
        return purchaserService.create(purchaserDto);
    }

    @PutMapping("/update")
    public PurchaserDto update(@RequestParam(value = "id") Long id, @RequestBody PurchaserDto purchaserDto) {
        return purchaserService.update(id, purchaserDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        purchaserService.delete(id);
    }
}
