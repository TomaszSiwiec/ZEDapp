package com.zedapp.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto implements Serializable {
    private String id;

    private PurchaserDto purchaser;

    private String comment;

    private List<ElementDto> elements;

    private List<FileDto> files;
}
