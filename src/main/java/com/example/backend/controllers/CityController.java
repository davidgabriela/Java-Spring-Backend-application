package com.example.backend.controllers;

import com.example.backend.models.City;
import com.example.backend.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CityController {

    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) { this.cityRepository = cityRepository; }

    @GetMapping("/cities")
    public List<City> getCities() {
        return cityRepository.findAll();
    }
    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable Integer id) {
        return cityRepository.getCityById(id);
    }

    @DeleteMapping("/cities/{id}")
    public void deleteCityById(@PathVariable Integer id) {
        cityRepository.deleteById(id);
    }

    @PutMapping("/cities/{id}")
    public City updateCityName(@PathVariable Integer id, @RequestBody City city) {
        City newCity = cityRepository.getCityById(id);
        newCity.setName(city.getName());
        return cityRepository.save(newCity);
    }
}