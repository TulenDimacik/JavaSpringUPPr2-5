package com.example.demo.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String building;

    @OneToMany (mappedBy = "address", fetch = FetchType.EAGER)
    private Collection<Post> tenants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Collection<Post> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Post> tenants) {
        this.tenants = tenants;
    }
}
