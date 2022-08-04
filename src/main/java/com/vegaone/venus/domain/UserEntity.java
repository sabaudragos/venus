package com.vegaone.venus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<ProjectEntity> project;
    private String email;
}
