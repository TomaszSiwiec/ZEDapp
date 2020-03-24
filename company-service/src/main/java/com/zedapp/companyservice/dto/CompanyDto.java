package com.zedapp.companyservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class CompanyDto implements Serializable {
    private String id;

    private Long orderId;

    private String nip;

    private String name;

    private String street;

    private String buildingNumber;

    private String localNumber;

    private String zipCode;

    private String city;

    private String country;

    private List<PurchaserDto> purchaserDtos;
}
