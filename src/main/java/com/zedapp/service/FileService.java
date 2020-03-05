package com.zedapp.service;

import com.zedapp.domain.File;
import com.zedapp.domain.dto.FileDto;
import com.zedapp.mapper.FileMapper;
import com.zedapp.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@Slf4j
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    public List<FileDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity FILES");
        return fileMapper.mapToDtoList(fileRepository.findAll());
    }

    public FileDto get(long id) {
        File file = fileRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity FILES");
        return fileMapper.mapToDto(file);
    }

    public FileDto create(FileDto fileDto) {
        File file = new File();
        file.setFilename(fileDto.getFilename());
        file.setUuid(fileDto.getUuid());
        file.setDocumentType(fileDto.getDocumentType());
        log.info("[ZEDAPP] Added new object with name: " + file.getFilename() + " to entity FILES");
        return fileMapper.mapToDto(fileRepository.save(file));
    }

    public FileDto update(long id, FileDto fileDto) {
        File file = fileRepository.findOrThrow(id);
        file.setFilename(fileDto.getFilename());
        file.setUuid(fileDto.getUuid());
        file.setDocumentType(fileDto.getDocumentType());
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity FILES");
        return fileMapper.mapToDto(fileRepository.save(file));
    }

    public void delete(long id) {
        fileRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity FILES");
    }
}
