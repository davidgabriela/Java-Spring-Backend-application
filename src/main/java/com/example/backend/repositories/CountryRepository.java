package com.example.backend.repositories;

import com.example.backend.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country getCountryById(Integer id);
    void deleteCountryById(Integer id);
}