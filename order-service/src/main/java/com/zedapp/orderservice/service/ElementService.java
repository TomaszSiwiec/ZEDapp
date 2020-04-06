package com.zedapp.orderservice.service;

import com.zedapp.orderservice.dto.ElementDto;

import java.util.List;

public interface ElementService {
    List<ElementDto> getElementsByIds(List<String> ids);
}
