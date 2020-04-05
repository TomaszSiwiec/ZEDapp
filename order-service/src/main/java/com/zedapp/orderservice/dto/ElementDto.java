package com.zedapp.orderservice.dto;

import com.zedapp.orderservice.domain.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ElementDto implements Serializable {
    private String id;

    private String name;

    private String destination;

    private Status status;

    private List<FileDto> files;
}
