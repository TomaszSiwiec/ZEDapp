package com.zedapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMPANIES")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nip;

    private String name;

    private String street;

    private String buildingNumber;

    private String localNumber;

    private String zipCode;

    private String city;

    private String country;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_COMPANIES_PURCHASERS",
            joinColumns = {@JoinColumn(name = "COMPANIES_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PURCHASERS_ID",referencedColumnName = "ID")}
    )
    private List<Purchaser> purchasers;
}
