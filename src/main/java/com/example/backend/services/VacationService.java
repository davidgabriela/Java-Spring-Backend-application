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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

        Date vacationStartDate, vacationEndDate, sportStartMonth, sportEndMonth;
        LocalDate localStartDate, localEndDate, localSportStartMonth, localSportEndMonth;

        Long daysBetweenDates;

        try {
            vacationStartDate = dayMonthFormat.parse(startDate);
            vacationEndDate = dayMonthFormat.parse(endDate);
            localStartDate = vacationStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localEndDate = vacationEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if(localStartDate.getMonthValue() > localEndDate.getMonthValue())
                localEndDate = localEndDate.plusYears(1);

            daysBetweenDates = ChronoUnit.DAYS.between(localStartDate, localEndDate);

            allSportsList = getSportsFromLocation(id);

            for(String sportName : sports) {
                for(Sport s : allSportsList) {
                    if (s.getName().equals(sportName)) {

                        sportStartMonth = monthFormat.parse(s.getStartMonth().toString());
                        sportEndMonth = monthFormat.parse(s.getEndMonth().toString());
                        localSportStartMonth = sportStartMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        localSportEndMonth = sportEndMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        if(localSportStartMonth.getMonthValue() > localSportEndMonth.getMonthValue()) {
                            localSportEndMonth = localSportEndMonth.plusYears(1);

                            System.out.println("start month =  " + localSportStartMonth);
                            System.out.println("end month = " + localSportEndMonth);
                        }


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

