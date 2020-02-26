package com.zedapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private long id;
    private String name;
    private String comments;
    private LocalDateTime dateOfCreation;
    private List<ElementDto> elementDtos;
    private List<PurchaserDto> purchaserDtos;
    private UserDto addedByDto;
}
