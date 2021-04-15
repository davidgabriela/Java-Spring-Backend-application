package com.example.backend.models;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer pricePerDay;

    public Sport() {}
    public Sport(String name) {
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

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Integer getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(Integer pricePerDay) { this.pricePerDay = pricePerDay; }

    @Override
    public String toString() {
        return "Sport { sport_id =  " + id + ", name = " + name +
                " start_date= " + startDate + " end_date= " + endDate + " price= " + pricePerDay +  " }";
    }
}