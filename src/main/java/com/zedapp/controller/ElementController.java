package com.zedapp.controller;

import com.zedapp.domain.dto.ElementDto;
import com.zedapp.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/element")
public class ElementController {
    @Autowired
    private ElementService elementService;

    @GetMapping("/getAll")
    public List<ElementDto> getAll() {
        return elementService.getAll();
    }

    @GetMapping("/get")
    public ElementDto getById(@RequestParam(value = "id") Long id) {
        return elementService.get(id);
    }

    @PostMapping("/create")
    public ElementDto create(@RequestBody ElementDto elementDto) {
        return elementService.create(elementDto);
    }

    @PutMapping("/update")
    public ElementDto update(@RequestParam(value = "id") Long id, @RequestBody ElementDto elementDto) {
        return elementService.update(id, elementDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        elementService.delete(id);
    }
}
