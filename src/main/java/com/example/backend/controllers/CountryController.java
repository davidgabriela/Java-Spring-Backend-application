package com.example.backend.controllers;

import com.example.backend.repositories.CountryRepository;
import com.example.backend.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryRepository.getCountriesBy();
    }

    @GetMapping("/country/{id}")
    public Country getCountryById(@PathVariable Integer id) {
        return countryRepository.getCountryById(id);
    }

    @PostMapping("/country")
    public Country addCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }


}