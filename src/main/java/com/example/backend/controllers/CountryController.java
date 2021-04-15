package com.example.backend.controllers;

import com.example.backend.models.County;
import com.example.backend.repositories.CountryRepository;
import com.example.backend.repositories.CountyRepository;
import com.example.backend.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CountryController {

    private final CountryRepository countryRepository;
    private final CountyRepository countyRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository, CountyRepository countyRepository) {
        this.countryRepository = countryRepository;
        this.countyRepository = countyRepository;
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/countries/{id}")
    public Country getCountryById(@PathVariable Integer id) {
        return countryRepository.getCountryById(id);
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    @DeleteMapping("/countries/{id}")
    public void deleteCountryById(@PathVariable Integer id) {
        countryRepository.deleteById(id);
    }

    @PutMapping("/countries/{id}")
    public Country updateCountryName(@PathVariable Integer id, @RequestBody Country country) {
        Country newCountry = countryRepository.getCountryById(id);
        newCountry.setName(country.getName());
        return countryRepository.save(newCountry);
    }

    @PostMapping("/countries/{id}/counties")
    public Country addCountyToCountry(@PathVariable Integer id, @RequestBody County county) {
        Country country = countryRepository.getCountryById(id);
        County newCounty = countyRepository.save(county);
        List<County> counties = country.getCounties();
        counties.add(newCounty);
        country.setCounties(counties);
        return countryRepository.save(country);
    }
}