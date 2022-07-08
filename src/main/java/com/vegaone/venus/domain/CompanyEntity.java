package com.vegaone.venus.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company")
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
}
