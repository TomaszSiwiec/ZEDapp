package com.zedapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String nip;
    private String name;
    private String street;
    private String buildingNumber;
    private String localNumber;
    private String zipCode;
    private String city;
    private String country;
}
