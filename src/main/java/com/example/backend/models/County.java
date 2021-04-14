package com.example.backend.models;

import javax.persistence.*;

@Entity
@Table
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer countryId;
    private Integer countyId;
    private String name;

    public County() {}
    public County(String name) {
        this.name = name;
    }

    public Integer getCountryId() {
        return this.countryId;
    }

    public Integer getCountyId() {
        return this.countyId;
    }

    public String getName() {
        return this.name;
    }

    public void setCountryIdId(Integer id) { this.countryId = id; }

    public void setCountyIdId(Integer id) { this.countyId = id; }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "County { country_id = " + countryId + ", county_id =  " + countyId + ", name = " + name;
    }

}