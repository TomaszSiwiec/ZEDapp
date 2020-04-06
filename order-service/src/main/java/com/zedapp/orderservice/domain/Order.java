package com.zedapp.orderservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Order implements Serializable {

    @MongoId
    private String id;

    private String purchaserId;

    private String comment;

    private List<String> elementsIds;

    private List<String> filesIds;
}
