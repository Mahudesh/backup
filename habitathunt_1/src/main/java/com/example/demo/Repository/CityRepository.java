package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Modal.City;

public interface CityRepository extends JpaRepository<City, Integer> {
}
