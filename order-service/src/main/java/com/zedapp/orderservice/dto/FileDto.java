package com.zedapp.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class FileDto implements Serializable {
    private String id;

    private String filename;

    private String uuid;

    private String extension;
}
