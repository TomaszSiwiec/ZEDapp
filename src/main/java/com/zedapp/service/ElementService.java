package com.zedapp.service;

import com.zedapp.domain.Element;
import com.zedapp.domain.dto.ElementDto;
import com.zedapp.mapper.ElementMapper;
import com.zedapp.repository.ElementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class ElementService {

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private ElementMapper elementMapper;

    public List<ElementDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity ELEMENTS");
        return elementMapper.mapToDtoList(elementRepository.findAll());
    }

    public ElementDto get(long id) {
        Element element = elementRepository.findOrThrow(id);
        log.info("[ZEDAPP] Returned object with ID: " + id + " from entity ELEMENTS");
        return elementMapper.mapToDto(element);
    }

    public ElementDto create(ElementDto elementDto) {
        Element element = new Element();
        element.setName(elementDto.getName());
        element.setDestination(elementDto.getDestination());
        element.setStatus(elementDto.getStatus());
        log.info("[ZEDAPP] Added new object with name: " + element.getName() + " to entity ELEMENTS");
        return elementMapper.mapToDto(elementRepository.save(element));
    }

    public ElementDto update(long id, ElementDto elementDto) {
        Element element = elementRepository.findOrThrow(id);
        element.setName(elementDto.getName());
        element.setDestination(elementDto.getDestination());
        element.setStatus(elementDto.getStatus());
        log.info("[ZEDAPP] Updated object with ID: " + id +  " from entity ELEMENTS");
        return elementMapper.mapToDto(elementRepository.save(element));
    }

    public void delete(long id) {
        elementRepository.deleteById(id);
        log.info("[ZEDAPP] Deleted object with ID: " + id + " from entity ELEMENTS");
    }
}
