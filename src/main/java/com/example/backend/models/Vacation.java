package com.example.backend.models;

import java.util.Date;
import java.util.List;

public class Vacation {
    private String city;
    private Long estimatedCost;
    private List<String> sports;

    public Vacation() {}
    public Vacation(Date startDate,Date endDate, String city, Long estimatedCost, List<String> sports) {
        this.city = city;
        this.sports = sports;
        this.estimatedCost = estimatedCost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Long estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }
}
