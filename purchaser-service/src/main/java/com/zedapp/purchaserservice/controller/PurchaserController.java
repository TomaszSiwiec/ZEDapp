package com.zedapp.purchaserservice.controller;

import com.zedapp.purchaserservice.dto.PurchaserDto;
import com.zedapp.purchaserservice.service.PurchaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/purchaser")
public class PurchaserController {
    @Autowired
    private PurchaserService purchaserService;

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PurchaserDto>> getAll() {
        return purchaserService.getAll();
    }

    @GetMapping(value = "/getById/{purchaserId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaserDto> getById(@PathVariable("purchaserId") String id) {
        return purchaserService.getById(id);
    }

    @GetMapping(value = "/getByName/{purchaserName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PurchaserDto>> getByName(@PathVariable("purchaserName") String name) {
        return purchaserService.getByName(name);
    }

    @GetMapping(value = "/getByLastName/{purchaserLastName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PurchaserDto>> getByLastName(@PathVariable("purchaserLastName") String lastName) {
        return purchaserService.getByLastName(lastName);
    }

    @GetMapping(value = "/getByTelNumber/{telNumber}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PurchaserDto>> getByTelNumber(@PathVariable("telNumber")String telNumber) {
        return purchaserService.getByTelNumber(telNumber);
    }

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaserDto> create(PurchaserDto purchaserDto) {
        return purchaserService.create(purchaserDto);
    }

    @PutMapping(value = "/update/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaserDto> update(@PathVariable("id") String id, PurchaserDto purchaserDto) {
        return purchaserService.update(id, purchaserDto);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        return purchaserService.deleteById(id);
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity deleteAll() {
        return purchaserService.deleteAll();
    }
}
