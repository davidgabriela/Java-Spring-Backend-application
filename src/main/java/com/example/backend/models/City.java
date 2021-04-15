package com.example.backend.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_cities_sports_id"))
    private List<Sport> sports;

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

    public List<Sport> getSports() { return sports; }
    public void setSports(List<Sport> sports) { this.sports = sports; }

    @Override
    public String toString() {
        return "City { city_id =  " + id + ", name = " + name + " }";
    }
}