package com.zedapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String mail;

    private String name;

    private String lastname;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "addedBy",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Order> orders;
}
