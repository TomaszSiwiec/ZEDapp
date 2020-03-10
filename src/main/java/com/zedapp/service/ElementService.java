package com.zedapp.service;

import com.zedapp.domain.Element;
import com.zedapp.domain.File;
import com.zedapp.domain.dto.ElementDto;
import com.zedapp.mapper.ElementMapper;
import com.zedapp.repository.ElementRepository;
import lombok.extern.slf4j.Slf4j;
import com.zedapp.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class ElementService {

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ElementMapper elementMapper;

    public List<ElementDto> getAll() {
        log.info("[ZEDAPP] Returned all object from entity ELEMENTS");
        return elementMapper.mapToDtoList(elementRepository.findAll());
    }

    public List<ElementDto> getAllByOrderId(Long orderId) {
        List<Element> allElements = elementRepository.findAll();
        if (allElements.isEmpty() || allElements == null) {
            return elementMapper.mapToDtoList(new ArrayList<>());
        }

        List<Element> filteredElements = allElements.stream()
                .filter(element -> element.getOrder().getId() == orderId)
                .collect(Collectors.toList());
        return elementMapper.mapToDtoList(filteredElements);
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

    public List<ElementDto> getAllByFileId(Long fileId) {
        File file = fileRepository.findOrThrow(fileId);
        List<Element> elements = elementRepository.findAll();
        List<Element> filteredElements = new ArrayList<>();

        for (Element element : elements) {
            if (element.getFiles().contains(file)) {
                filteredElements.add(element);
            }
        }
        log.info("[ZEDAPP] Returned all objects with File ID: " + fileId + " from entity ELEMENTS");
        return elementMapper.mapToDtoList(filteredElements);
    }

    public ElementDto assignFile(Long elementid, Long fileId) {
        Element element = elementRepository.findOrThrow(elementid);
        File file = fileRepository.findOrThrow(fileId);

        List<Element> elements = file.getElements();
        List<File> files = element.getFiles();

        elements.add(element);
        files.add(file);

        element.setFiles(files);
        file.setElements(elements);

        fileRepository.save(file);
        log.info("[ZEDAPP] Assigned File object with ID: " + fileId + " to Element object with ID: " + elementid);
        return elementMapper.mapToDto(elementRepository.save(element));
    }
}
