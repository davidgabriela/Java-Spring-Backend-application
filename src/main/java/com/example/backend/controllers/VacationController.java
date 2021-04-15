package com.example.backend.controllers;

import com.example.backend.models.Vacation;
import com.example.backend.services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class VacationController {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/vacations?country_id={id},start={startDate},end={endDate},sports={sports}")
    public List<Vacation> getVacationsByCountry(@PathVariable Integer id, @PathVariable String startDate,
                                                @PathVariable String endDate, @PathVariable List<String> sports) {
        return vacationService.getVacationsByCountry(id, startDate, endDate, sports);
    }
}
