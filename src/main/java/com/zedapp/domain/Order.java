package com.zedapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String comments;

    private LocalDateTime dateOfCreation;

    @OneToMany(
            targetEntity = Element.class,
            mappedBy = "order",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Element> elements;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_PURCHASERS_ORDERS",
            joinColumns = {@JoinColumn(name = "ORDERS_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PURCHASERS_ID", referencedColumnName = "ID")}
    )
    private List<Purchaser> purchasers;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private User addedBy;
}
