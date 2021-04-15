package com.example.backend.repositories;

import com.example.backend.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    City getCityById(Integer id);
    void deleteCityById(Integer id);
}