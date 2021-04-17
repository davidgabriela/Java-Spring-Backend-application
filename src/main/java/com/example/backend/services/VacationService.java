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
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public List<Sport> getSportsFromLocation(Integer id) {
        Country country = new Country();
        County county = new County();
        City city = new City();

        List<County> allCounties = new ArrayList<County>();
        List<City> allCities = new ArrayList<City>();
        List<Sport> allSports = new ArrayList<Sport>();

        if(countryRepository.getCountryById(id) != null) {
            country = countryRepository.getCountryById(id);
            allCounties.addAll(country.getCounties());
            for(County c : allCounties) {
                if(c != null)
                    allCities.addAll(c.getCities());
            }
            for (City c : allCities) {
                if(c != null)
                    allSports.addAll(c.getSports());
            }
        }

        if(countyRepository.getCountyById(id) != null) {
            county = countyRepository.getCountyById(id);
            allCities.addAll(county.getCities());
            for (City c : allCities) {
                if(c != null)
                    allSports.addAll(c.getSports());
            }
        }

        if(cityRepository.getCityById(id) != null) {
            city = cityRepository.getCityById(id);
            if(city.getSports() != null)
                allSports.addAll(city.getSports());
            else
                allSports = null;
        }

        return allSports;
    }

    public String getSports(Integer id, String startDate, String endDate, List<String> sports) {
        List<Sport> sportList = new ArrayList<Sport>();

        sportList = getSportsFromLocation(id);

        return sportList.toString();
    }
}

