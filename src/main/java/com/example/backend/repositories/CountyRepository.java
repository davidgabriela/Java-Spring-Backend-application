package com.example.backend.repositories;

import com.example.backend.models.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County, Integer> {
    County getCountyById(Integer id);
    County getCountyByName(String name);
    void deleteCountyById(Integer id);
}