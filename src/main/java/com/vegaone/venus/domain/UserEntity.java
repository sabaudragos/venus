package com.vegaone.venus.domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Fetch(FetchMode.SELECT)
    private CompanyEntity company;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<ProjectEntity> project;

    @OneToMany(mappedBy = "user")
    private List<FeatureEntity> features;

    private String email;
}
