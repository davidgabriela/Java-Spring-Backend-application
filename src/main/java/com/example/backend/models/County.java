package com.example.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "county_id", foreignKey = @ForeignKey(name = "fk_counties_cities_id"))
    private List<City> cities;

    public County() {}
    public County(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return this.name;
    }
    public void setName(String name) { this.name = name; }

    public List<City> getCities() { return this.cities; }
    public void setCities(List<City> cities) { this.cities = cities; }

    @Override
    public String toString() {
        return "County { name = " + name + " cities = " + cities.toString() + " }";
    }

}