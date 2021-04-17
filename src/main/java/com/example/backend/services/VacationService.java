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
import java.time.LocalDate;
import java.time.ZoneId;
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
        List<Sport> allSportsList = new ArrayList<Sport>();
        List<Sport> sportsList = new ArrayList<Sport>();

        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat dayMonthFormat = new SimpleDateFormat("dd-MM");

        Date vacationStartDate, vacationEndDate;
        Date sportStartMonth, sportEndMonth;

        LocalDate startLocalDate, endLocalDate;

        try {
            vacationStartDate = dayMonthFormat.parse(startDate);
            vacationEndDate = dayMonthFormat.parse(endDate);
            startLocalDate = vacationStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            endLocalDate = vacationEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if(startLocalDate.getMonthValue() > endLocalDate.getMonthValue())
                endLocalDate = endLocalDate.plusYears(1);

            System.out.println(startLocalDate);
            System.out.println(endLocalDate);

            allSportsList = getSportsFromLocation(id);

            for(String sportName : sports) {
                for(Sport s : allSportsList) {
                    if (s.getName().equals(sportName)) {
                        sportsList.add(s);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sportsList.toString();
    }
}

