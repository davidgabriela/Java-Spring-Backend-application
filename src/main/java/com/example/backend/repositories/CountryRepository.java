package com.example.backend.repositories;

import com.example.backend.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country getCountryById(Integer id);
    Country getCountryByName(String name);
    void deleteCountryById(Integer id);
}