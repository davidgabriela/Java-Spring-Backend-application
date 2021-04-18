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
    private Integer startMonth;
    private Integer endMonth;
    private Long pricePerDay;

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

    public Integer getStartMonth() { return startMonth; }
    public void setStartMonth(Integer startMonth) { this.startMonth = startMonth; }

    public Integer getEndMonth() { return endMonth; }
    public void setEndMonth(Integer endMonth) { this.endMonth = endMonth; }

    public Long getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(Long pricePerDay) { this.pricePerDay = pricePerDay; }

    @Override
    public String toString() {
        return "Sport { name = " + name + ", start_month = " + startMonth + ", end_month = " + endMonth +
                ", price per day = " + pricePerDay +  " }";
    }
}