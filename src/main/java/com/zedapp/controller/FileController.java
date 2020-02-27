package com.zedapp.controller;

import com.zedapp.domain.dto.FileDto;
import com.zedapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zedapp/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/getAll")
    public List<FileDto> getAll() {
        return fileService.getAll();
    }

    @GetMapping("/get")
    public FileDto getById(@RequestParam(value = "id") Long id) {
        return fileService.get(id);
    }

    @PostMapping("/create")
    public FileDto create(@RequestBody FileDto fileDto) {
        return fileService.create(fileDto);
    }

    @PutMapping("/update")
    public FileDto update(@RequestParam(value = "id") Long id, @RequestBody FileDto fileDto) {
        return fileService.update(id, fileDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        fileService.delete(id);
    }
}
