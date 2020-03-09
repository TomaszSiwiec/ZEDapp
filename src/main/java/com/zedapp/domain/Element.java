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
@Table(name = "ELEMENTS")
public class Element implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String destination;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "elements")
    private List<File> files;

    @ManyToOne
    @JoinColumn(name = "ORDERS_ID")
    private Order order;
}
