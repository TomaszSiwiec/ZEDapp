package com.zedapp.purchaserservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Document
public class Purchaser implements Serializable {
    @MongoId
    private String id;
    private String name;
    private String lastname;
    private String telNumber;
}