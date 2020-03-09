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

    @GetMapping("/getAllByOrderId/{orderId}")
    public List<ElementDto> getAllByOrderId(@PathVariable(name = "orderId") Long orderId) {
        return elementService.getAllByOrderId(orderId);
    }

    @GetMapping("/getAllByFileId")
    public List<ElementDto> getAllByFileId(@RequestParam(value = "fileId") Long fileId) {
        return elementService.getAllByFileId(fileId);
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

    @PutMapping("/assignFile")
    public ElementDto assignFile(@RequestParam(value = "elementId") Long elementid, @RequestParam(value = "fileId") Long fileId) {
        return elementService.assignFile(elementid, fileId);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        elementService.delete(id);
    }
}
