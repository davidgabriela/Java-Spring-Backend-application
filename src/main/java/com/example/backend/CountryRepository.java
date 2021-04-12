package com.example.backend;

import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
    long deleteCountryByName(String name);
    Country findCountryById(Integer id);
}