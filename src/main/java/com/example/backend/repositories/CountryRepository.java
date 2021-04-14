package com.example.backend.repositories;

import com.example.backend.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> getCountriesBy();
    Country getCountryById(Integer id);
}