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
@Table(name = "PURCHASERS")
public class Purchaser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String lastname;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_COMPANIES_PURCHASERS",
            joinColumns = {@JoinColumn(name = "PURCHASERS_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "COMPANIES_ID", referencedColumnName = "ID")}
    )
    private List<Company> companies;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_PURCHASERS_ORDERS",
            joinColumns = {@JoinColumn(name = "PURCHASERS_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDERS_ID",referencedColumnName = "ID")}
    )
    private List<Order> orders;
}
