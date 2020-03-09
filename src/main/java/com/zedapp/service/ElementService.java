package com.zedapp.service;

import com.zedapp.domain.Element;
import com.zedapp.domain.File;
import com.zedapp.domain.dto.ElementDto;
import com.zedapp.mapper.ElementMapper;
import com.zedapp.repository.ElementRepository;
import com.zedapp.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ElementService {

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ElementMapper elementMapper;

    public List<ElementDto> getAll() {
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

    public List<ElementDto> getAllByFileId(Long fileId) {
        File file = fileRepository.findOrThrow(fileId);
        List<Element> elements = elementRepository.findAll();
        List<Element> filteredElements = new ArrayList<>();

        for (Element element : elements) {
            if (element.getFiles().contains(file)) {
                filteredElements.add(element);
            }
        }

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
        return elementMapper.mapToDto(elementRepository.save(element));
    }
}
