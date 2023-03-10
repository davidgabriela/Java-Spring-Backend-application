package com.example.backend.repositories;

import com.example.backend.models.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Integer> {
    Sport getSportById(Integer id);
    void deleteSportById(Integer id);
}