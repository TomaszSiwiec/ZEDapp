package com.zedapp.service;

import com.zedapp.domain.File;
import com.zedapp.domain.dto.FileDto;
import com.zedapp.mapper.FileMapper;
import com.zedapp.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    public List<FileDto> getAll() {
        return fileMapper.mapToDtoList(fileRepository.findAll());
    }

    public FileDto get(long id) {
        File file = fileRepository.findOrThrow(id);
        return fileMapper.mapToDto(file);
    }

    public FileDto create(FileDto fileDto) {
        File file = new File();
        file.setFilename(fileDto.getFilename());
        file.setUuid(fileDto.getUuid());
        file.setDocumentType(fileDto.getDocumentType());
        return fileMapper.mapToDto(fileRepository.save(file));
    }

    public FileDto update(long id, FileDto fileDto) {
        File file = fileRepository.findOrThrow(id);
        file.setFilename(fileDto.getFilename());
        file.setUuid(fileDto.getUuid());
        file.setDocumentType(fileDto.getDocumentType());
        return fileMapper.mapToDto(fileRepository.save(file));
    }

    public void delete(long id) {
        fileRepository.deleteById(id);
    }
}
