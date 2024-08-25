package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Modal.City;
import com.example.demo.Modal.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> 
{
	List<Property> findByCity(City city); 

	 Property findByCityAndPropertyId(City city, Integer propertyId); 
	 List<Property> findByOwner_OwnerId(Integer ownerId); 
}