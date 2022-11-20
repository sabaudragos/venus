package com.vegaone.venus.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "project")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

}
