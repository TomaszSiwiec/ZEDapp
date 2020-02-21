package com.zedapp.mapper;

import com.zedapp.domain.File;
import com.zedapp.domain.dto.FileDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileMapper {
    public FileDto mapToDto(File file) {
        if (file == null)
            return null;
        return new FileDto(
            file.getFilename(),
                file.getUuid(),
                file.getDocumentType()
        );
    }

    public File mapToEntity(FileDto fileDto) {
        if (fileDto == null)
            return null;
        return new File(
                0L,
                fileDto.getFilename(),
                fileDto.getUuid(),
                fileDto.getDocumentType(),
                null
        );
    }

    public List<FileDto> mapToDtoList(List<File> fileList) {
        if (fileList.isEmpty())
            return new ArrayList<>();
        if (fileList == null)
            return null;

        return fileList.stream()
                .map(file -> mapToDto(file))
                .collect(Collectors.toList());
    }

    public List<File> mapToEntityList(List<FileDto> fileDtoList) {
        if (fileDtoList.isEmpty())
            return new ArrayList<>();
        if (fileDtoList == null)
            return null;

        return fileDtoList.stream()
                .map(fileDto -> mapToEntity(fileDto))
                .collect(Collectors.toList());
    }
}
