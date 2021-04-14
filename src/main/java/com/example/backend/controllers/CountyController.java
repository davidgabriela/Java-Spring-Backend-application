package com.example.backend.controllers;

import com.example.backend.models.Country;
import com.example.backend.models.County;
import com.example.backend.repositories.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CountyController {

    private final CountyRepository countyRepository;

    @Autowired
    public CountyController(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
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
        County oldCounty = countyRepository.getCountyById(id);
        oldCounty.setName(county.getName());
        return countyRepository.save(oldCounty);
    }
}