package com.zedapp.service;

import com.zedapp.domain.Element;
import com.zedapp.domain.dto.ElementDto;
import com.zedapp.mapper.ElementMapper;
import com.zedapp.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ElementService {

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private ElementMapper elementMapper;

    public List<ElementDto> getAll() {
        return elementMapper.mapToDtoList(elementRepository.findAll());
    }

    public ElementDto get(long id) {
        Element element = elementRepository.findOrThrow(id);
        return elementMapper.mapToDto(element);
    }

    public ElementDto create(ElementDto elementDto) {
        Element element = new Element();
        element.setName(elementDto.getName());
        element.setDestination(elementDto.getDestination());
        element.setStatus(elementDto.getStatus());
        return elementMapper.mapToDto(elementRepository.save(element));
    }

    public ElementDto update(long id, ElementDto elementDto) {
        Element element = elementRepository.findOrThrow(id);
        element.setName(elementDto.getName());
        element.setDestination(elementDto.getDestination());
        element.setStatus(elementDto.getStatus());
        return elementMapper.mapToDto(elementRepository.save(element));
    }

    public void delete(long id) {
        elementRepository.deleteById(id);
    }
}
