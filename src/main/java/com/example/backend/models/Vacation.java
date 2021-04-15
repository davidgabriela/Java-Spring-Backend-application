package com.example.backend.models;

import java.util.Date;
import java.util.List;

public class Vacation {
    private Date startDate;
    private Date endDate;
    private City city;
    private Integer estimatedCost;
    private List<String> sports;

    public Vacation() {}
    public Vacation(Date startDate,Date endDate, City city, Integer estimatedCost, List<String> sports) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.estimatedCost = estimatedCost;
        this.sports = sports;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Integer estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }
}
