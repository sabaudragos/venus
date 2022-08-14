package com.vegaone.venus.domain;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "company", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProjectEntity> projects = new ArrayList<>();

    @OneToMany(mappedBy = "company", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserEntity> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        if (CollectionUtils.isNotEmpty(projects)) {
            this.projects.addAll(projects);
        }
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        if (CollectionUtils.isNotEmpty(users)) {
            this.users.addAll(users);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) && Objects.equals(country, that.country) &&
                Objects.equals(phone, that.phone) && Objects.equals(registrationNumber, that.registrationNumber) &&
                Objects.equals(email, that.email) && Objects.equals(vatNumber, that.vatNumber) &&
                Objects.equals(projects, that.projects) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, city, country, phone, registrationNumber, email, vatNumber, projects, users);
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", email='" + email + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", projects=" + projects +
                ", users=" + users +
                '}';
    }
}
