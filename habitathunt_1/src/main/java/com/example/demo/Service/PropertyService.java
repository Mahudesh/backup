package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Modal.City;
import com.example.demo.Modal.Property;
import com.example.demo.Repository.CityRepository;
import com.example.demo.Repository.PropertyRepository;
@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CityRepository cityRepository; 	 

    public List<Property> getPropertiesByCity(Integer cityId) {
       
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));
        return propertyRepository.findByCity(city);
    }

    public Property getPropertyByCityAndId(Integer cityId, Integer propertyId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));
        return propertyRepository.findByCityAndPropertyId(city, propertyId);
    }
    public void deletePropertyById(Integer propertyId) {	
        propertyRepository.deleteById(propertyId);
    }	

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }
    public Optional<Property> findById(Integer propertyId) {
        return propertyRepository.findById(propertyId);
    }
}


//public List<Property> getAllProperties() {
//return propertyRepository.findAll();
//}
//public List<Property> getPropertiesByCityId(Integer cityId) {
//return propertyRepository.findByCityId(cityId);
//}
//
//public Property getPropertyById(Integer id) {
//return propertyRepository.findById(id).orElse(null);
//}