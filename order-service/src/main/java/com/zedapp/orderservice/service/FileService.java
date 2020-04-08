package com.zedapp.orderservice.service;

import com.zedapp.orderservice.dto.FileDto;

import java.util.List;

public interface FileService {
    List<FileDto> getFilesByIds(List<String> ids);
}
