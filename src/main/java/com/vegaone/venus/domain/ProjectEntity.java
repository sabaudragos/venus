package com.vegaone.venus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private CompanyEntity company;
    @OneToOne(mappedBy = "project")
    private UserEntity user;

}
