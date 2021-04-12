package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BackendController {
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/add")
    public String addCountry(@RequestParam String name) {
        Country country = new Country();
        country.setName(name);
        countryRepository.save(country);
        return "Added new country to repo!";
    }

    @DeleteMapping("/remove")
    public String removeCountry(@RequestParam String name) {
        long deletedRecords = countryRepository.deleteCountryByName(name);
        return "Removed country to repo!";
    }

    @GetMapping("/list")
    public Iterable<Country> getCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Country findCountryById(@PathVariable Integer id) {
        return countryRepository.findCountryById(id);
    }
}