package com.example.backend.controllers;

import com.example.backend.models.Sport;
import com.example.backend.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class SportController {

    private final SportRepository sportRepository;

    @Autowired
    public SportController(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @GetMapping("/sports")
    public List<Sport> getSports() {
        return sportRepository.findAll();
    }
    @GetMapping("/sports/{id}")
    public Sport getSportById(@PathVariable Integer id) {
        return sportRepository.getSportById(id);
    }

    @DeleteMapping("/sports/{id}")
    public void deleteSportById(@PathVariable Integer id) {
        sportRepository.deleteById(id);
    }

    @PutMapping("/sports/{id}")
    public Sport updateSportData(@PathVariable Integer id, @RequestBody Sport sport) {
        Sport newSport = sportRepository.getSportById(id);
        if(sport.getName() != null)
            newSport.setName(sport.getName());
        if(sport.getStartDate() != null)
            newSport.setStartDate(sport.getStartDate());
        if(sport.getEndDate() != null)
            newSport.setEndDate(sport.getEndDate());
        if(sport.getPricePerDay() != null)
            newSport.setPricePerDay(sport.getPricePerDay());
        return sportRepository.save(newSport);
    }
}