package com.example.backend.services;

import com.example.backend.models.Country;
import com.example.backend.models.County;
import com.example.backend.models.City;
import com.example.backend.models.Sport;
import com.example.backend.models.Vacation;
import com.example.backend.repositories.CityRepository;
import com.example.backend.repositories.CountyRepository;
import com.example.backend.repositories.CountryRepository;
import com.example.backend.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VacationService {
    private final CountryRepository countryRepository;
    private final CountyRepository countyRepository;
    private final CityRepository cityRepository;
    private final SportRepository sportRepository;

    @Autowired
    public VacationService(CountryRepository countryRepository,
                           CountyRepository countyRepository,
                           CityRepository cityRepository,
                           SportRepository sportRepository) {
        this.countryRepository = countryRepository;
        this.countyRepository = countyRepository;
        this.cityRepository = cityRepository;
        this.sportRepository = sportRepository;
    }

    public List<Vacation> getVacationsByCountry(Integer countryId, String startDate,
                                                String endDate, List<String> sports) {
        Country country = countryRepository.getCountryById(countryId);
        List<County> counties = country.getCounties();
        List<City> cities = new ArrayList<City>();
        List<Vacation> vacations = new ArrayList<Vacation>();

        Integer startDateMonthNumber = Integer.parseInt(startDate.substring(startDate.indexOf("/") + 1));
        Integer endDateMonthNumber = Integer.parseInt(endDate.substring(endDate.indexOf("/") + 1));

        try {
            Date start = new SimpleDateFormat("dd/MM").parse(startDate);
            Date end = new SimpleDateFormat("dd/MM").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(County county : counties) {
            cities.addAll(county.getCities());
        }
        for(City city : cities) {
            Vacation newVacation;
            List<String> vacationSports = new ArrayList<String>();
            Integer vacationPrice = 0;
            List<Sport> citySports = city.getSports();
            for(Sport sport : citySports) {
                if(sports.contains(sport.getName())){
                    if(startDateMonthNumber >= sport.getStartMonth() &&
                            endDateMonthNumber <= sport.getEndMonth()){
                        vacationSports.add(sport.getName());
                    }
                }
            }
        }
        return new ArrayList<Vacation>();
    }
}

