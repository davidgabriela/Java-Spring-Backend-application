package com.example.backend.controllers;

import com.example.backend.models.City;
import com.example.backend.models.Sport;
import com.example.backend.repositories.CityRepository;
import com.example.backend.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CityController {

    private final CityRepository cityRepository;
    private final SportRepository sportRepository;

    @Autowired
    public CityController(CityRepository cityRepository, SportRepository sportRepository) {
        this.cityRepository = cityRepository;
        this.sportRepository = sportRepository;
    }

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

    @PostMapping("/cities/{id}/sports")
    public City addSportToCity(@PathVariable Integer id, @RequestBody Sport sport) {
        City city = cityRepository.getCityById(id);
        Sport newSport = sportRepository.save(sport);
        List<Sport> sports = city.getSports();
        sports.add(newSport);
        city.setSports(sports);
        return cityRepository.save(city);
    }
}