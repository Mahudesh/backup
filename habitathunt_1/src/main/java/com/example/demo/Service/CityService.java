package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Modal.City;
import com.example.demo.Repository.CityRepository;

@Service
public class CityService {
	
		@Autowired
	    private CityRepository cityRepository;

	    public City getCityById(Integer cityId) {
	        return cityRepository.findById(cityId).orElse(null);
	    }
	    
	    public List<City> getAllCities() {
	        return cityRepository.findAll();
	    }
}
