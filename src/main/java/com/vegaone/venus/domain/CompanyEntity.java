package com.vegaone.venus.domain;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "company")
@Entity
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String registrationNumber;
    private String email;
    private String vatNumber;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "company",
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProjectEntity> projects;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "company",
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserEntity> users;
}
