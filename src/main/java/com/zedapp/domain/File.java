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
@Table(name = "FILES")
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String filename;

    private String uuid;

    private String documentType;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_ELEMENTS_FILES",
            joinColumns = {@JoinColumn(name = "FILES_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ELEMENTS_ID", referencedColumnName = "ID")}
    )
    private List<Element> elements;
}
