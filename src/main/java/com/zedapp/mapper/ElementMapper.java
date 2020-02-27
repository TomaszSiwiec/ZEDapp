package com.zedapp.mapper;

import com.zedapp.domain.Element;
import com.zedapp.domain.File;
import com.zedapp.domain.dto.ElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElementMapper {

    public ElementDto mapToDto(Element element) {
        if (element == null)
            return null;
        return new ElementDto(
                element.getId(),
                element.getName(),
                element.getDestination(),
                element.getStatus()
        );
    }

    public Element mapToEntity(ElementDto elementDto) {
        if (elementDto == null)
            return null;
        return new Element(
                elementDto.getId(),
                elementDto.getName(),
                elementDto.getDestination(),
                elementDto.getStatus(),
                null,
                null
        );
    }

    public List<ElementDto> mapToDtoList(List<Element> elementList) {
        if (elementList == null)
            return null;

        return elementList.stream()
                .map(element -> mapToDto(element))
                .collect(Collectors.toList());
    }

    public List<Element> mapToEntityList(List<ElementDto> elementDtoList) {
        if (elementDtoList == null)
            return null;

        return elementDtoList.stream()
                .map(elementDto -> mapToEntity(elementDto))
                .collect(Collectors.toList());
    }
}
