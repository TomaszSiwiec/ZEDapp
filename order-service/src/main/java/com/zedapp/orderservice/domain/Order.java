package com.zedapp.orderservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Order implements Serializable {

    @Id
    private String _id;

    @Indexed(unique = true)
    private int internalId;

    private String purchaserId;

    private String comment;

    private List<String> elementsIds;

    private List<String> filesIds;
}
