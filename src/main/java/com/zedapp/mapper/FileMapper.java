package com.zedapp.mapper;

import com.zedapp.domain.File;
import com.zedapp.domain.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileMapper {

    @Autowired
    private ElementMapper elementMapper;

    public FileDto mapToDto(File file) {
        if (file == null)
            return null;
        return new FileDto(
                file.getId(),
                file.getFilename(),
                file.getUuid(),
                file.getDocumentType(),
                elementMapper.mapToDtoList(file.getElements())
        );
    }

    public File mapToEntity(FileDto fileDto) {
        if (fileDto == null)
            return null;
        return new File(
                fileDto.getId(),
                fileDto.getFilename(),
                fileDto.getUuid(),
                fileDto.getDocumentType(),
                elementMapper.mapToEntityList(fileDto.getElementDtos())
        );
    }

    public List<FileDto> mapToDtoList(List<File> fileList) {
        if (fileList == null)
            return null;

        return fileList.stream()
                .map(file -> mapToDto(file))
                .collect(Collectors.toList());
    }

    public List<File> mapToEntityList(List<FileDto> fileDtoList) {
        if (fileDtoList == null)
            return null;

        return fileDtoList.stream()
                .map(fileDto -> mapToEntity(fileDto))
                .collect(Collectors.toList());
    }
}
