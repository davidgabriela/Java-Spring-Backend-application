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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/vacations/{id}/{startDate}/{endDate}/{sports}")
    public List<Vacation> getVacationsByCountry(@PathVariable Integer id, @PathVariable String startDate,
                                             @PathVariable String endDate, @PathVariable List<String> sports) {
        return vacationService.getSports(id, startDate, endDate, sports);
    }
}
