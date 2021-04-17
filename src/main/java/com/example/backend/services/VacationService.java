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

        Date vacationStart, vacationEnd, sportStartMonth, sportEndMonth;
        LocalDate localStart, localEnd, localStartMonth, localEndMonth;

        Long daysBetweenDates;
        Long vacationPrice = 0L;

        try {
            vacationStart = dayMonthFormat.parse(startDate);
            vacationEnd = dayMonthFormat.parse(endDate);
            localStart = vacationStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localEnd = vacationEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if(localStart.getMonthValue() > localEnd.getMonthValue())
                localEnd = localEnd.plusYears(1);

            daysBetweenDates = ChronoUnit.DAYS.between(localStart, localEnd) + 1; // including last day

            allSportsList = getSportsFromLocation(id);

            for(String sportName : sports) {
                for(Sport s : allSportsList) {
                    if (s.getName().equals(sportName)) {

                        sportStartMonth = monthFormat.parse(s.getStartMonth().toString());
                        sportEndMonth = monthFormat.parse(s.getEndMonth().toString());
                        localStartMonth = sportStartMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        localEndMonth = sportEndMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        localEndMonth.plusMonths(1);

                        if(localStartMonth.getMonthValue() > localEndMonth.getMonthValue())
                            localEndMonth = localEndMonth.plusYears(1);

                        if((localStartMonth.isBefore(localStart) || localStartMonth.isEqual(localStart)) &&
                                localEnd.isBefore(localEndMonth)) {
                            sportsList.add(s);
                            vacationPrice += s.getPricePerDay() * daysBetweenDates;
                        }
                    }
                }
            }

            System.out.println(localStart.toString() + localEnd.toString() + "days to spend " + daysBetweenDates + ", total price = " + vacationPrice);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sportsList.toString();
    }
}

