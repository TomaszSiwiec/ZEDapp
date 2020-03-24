package com.zedapp.companyservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Company implements Serializable {
    @MongoId
    private String id;

    @Indexed(unique = true)
    private Long orderId;

    @Indexed(unique = true)
    private String nip;

    private String name;

    private String street;

    private String buildingNumber;

    private String localNumber;

    private String zipCode;

    private String city;

    private String country;

    private List<String> purchasersIds;
}
