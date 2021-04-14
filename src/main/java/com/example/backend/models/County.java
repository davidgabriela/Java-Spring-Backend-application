package com.example.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;

    public County() {}
    public County(String name) {
        this.name = name;
    }

    public Integer getCountyId() {
        return this.id;
    }

    public void setCountyId(Integer id) { this.id = id; }

    public String getName() {
        return this.name;
    }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "County { county_id =  " + id + ", name = " + name;
    }

}