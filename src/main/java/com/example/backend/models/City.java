package com.example.backend.models;
import javax.persistence.*;

@Entity
@Table
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;

    public City() {}
    public City(String name) {
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

    @Override
    public String toString() {
        return "City { city_id =  " + id + ", name = " + name + " }";
    }

}