package com.example.backend.models;

import java.util.List;
import javax.persistence.*;

@Entity
@Table
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_counties_countries_id"))
    private List<County> counties;

    public Country() {}

    public Country(String name) { this.name = name; }

    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public List<County> getCounties() { return this.counties; }
    public void setCounties(List<County> counties) { this.counties = counties; }

    @Override
    public String toString() {
        return "Country { name = " + name + " counties: " + counties.toString() + " }";
    }

}