package com.example.backend.controllers;

import com.example.backend.models.City;
import com.example.backend.models.County;
import com.example.backend.repositories.CountyRepository;
import com.example.backend.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CountyController {

    private final CountyRepository countyRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CountyController(CountyRepository countyRepository, CityRepository cityRepository) {
        this.countyRepository = countyRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/counties")
    public List<County> getCounties() {
        return countyRepository.findAll();
    }
    @GetMapping("/counties/{id}")
    public County getCountyById(@PathVariable Integer id) {
        return countyRepository.getCountyById(id);
    }

    @DeleteMapping("/counties/{id}")
    public void deleteCountyById(@PathVariable Integer id) {
        countyRepository.deleteById(id);
    }

    @PutMapping("/counties/{id}")
    public County updateCountyName(@PathVariable Integer id, @RequestBody County county) {
        County newCounty = countyRepository.getCountyById(id);
        newCounty.setName(county.getName());
        return countyRepository.save(newCounty);
    }

    @PostMapping("/counties/{id}/cities")
    public County addCityToCounty(@PathVariable Integer id, @RequestBody City city) {
        County county = countyRepository.getCountyById(id);
        City newCity = cityRepository.save(city);
        List<City> cities = county.getCities();
        cities.add(newCity);
        county.setCities(cities);
        return countyRepository.save(county);
    }
}