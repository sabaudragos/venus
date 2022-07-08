package com.vegaone.venus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String registrationNumber;
    private String email;
    private String vatNumber;
    private String administratorList;// TBD
}
