package com.example.backend.services;

import com.example.backend.models.*;
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

    public List<City> getCitiesFromLocation (Integer id) {
        Country country = new Country();
        County county = new County();
        City city = new City();

        List<County> allCounties = new ArrayList<County>();
        List<City> allCities = new ArrayList<City>();

        if(countryRepository.getCountryById(id) != null) {
            country = countryRepository.getCountryById(id);
            allCounties.addAll(country.getCounties());
            for(County c : allCounties) {
                if(c != null)
                    allCities.addAll(c.getCities());
            }
        }

        if(countyRepository.getCountyById(id) != null) {
            county = countyRepository.getCountyById(id);
            if(county.getCities() != null)
                allCities.addAll(county.getCities());
        }

        if(cityRepository.getCityById(id) != null) {
            city = cityRepository.getCityById(id);
            allCities.add(city);
        }

        return allCities;
    }

    public List<Vacation> getSports(Integer id, String startDate, String endDate, List<String> sports) {
        List<Sport> allSportsList;
        List<String> sportsList;
        List<Vacation> vacationsList = new ArrayList<Vacation>();
        List<City> allCities;

        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat dayMonthFormat = new SimpleDateFormat("dd-MM");

        Date vacationStart, vacationEnd, sportStartMonth, sportEndMonth;
        LocalDate localStart, localEnd, localStartMonth, localEndMonth;

        Long daysBetweenDates;
        Long vacationPrice;

        Vacation newVacation;

        allCities = getCitiesFromLocation(id);

        try {
            vacationStart = dayMonthFormat.parse(startDate);
            vacationEnd = dayMonthFormat.parse(endDate);
            localStart = vacationStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localEnd = vacationEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if(localStart.getMonthValue() > localEnd.getMonthValue())
                localEnd = localEnd.plusYears(1);

            daysBetweenDates = ChronoUnit.DAYS.between(localStart, localEnd) + 1; // including last day

            for(City c : allCities) {
                vacationPrice = 0L;
                newVacation = new Vacation();
                allSportsList = c.getSports();
                sportsList = new ArrayList<String>();
                for(Sport s : allSportsList) {
                    if (sports.contains(s.getName())) {

                        sportStartMonth = monthFormat.parse(s.getStartMonth().toString());
                        sportEndMonth = monthFormat.parse(s.getEndMonth().toString());
                        localStartMonth = sportStartMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        localEndMonth = sportEndMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        localEndMonth.plusMonths(1);

                        if(localStartMonth.getMonthValue() > localEndMonth.getMonthValue())
                            localEndMonth = localEndMonth.plusYears(1);

                        if((localStartMonth.isBefore(localStart) || localStartMonth.isEqual(localStart)) &&
                                localEnd.isBefore(localEndMonth)) {
                            sportsList.add(s.getName());
                            vacationPrice += s.getPricePerDay() * daysBetweenDates;
                        }
                    }
                }
                if(!sportsList.isEmpty()){
                    newVacation.setCity(c.getName());
                    newVacation.setSports(sportsList);
                    newVacation.setEstimatedCost(vacationPrice);
                    vacationsList.add(newVacation);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return vacationsList;
    }
}

