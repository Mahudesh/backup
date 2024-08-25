package com.example.demo.Service;

import com.example.demo.Modal.Owner;
import com.example.demo.Modal.Property;
import com.example.demo.Repository.OwnerRepository;
import com.example.demo.Repository.PropertyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PropertyRepository propertyRepository; 


    public Owner findByOwnerEmail(String ownerEmail) {
     
        return ownerRepository.findByOwnerEmail(ownerEmail);
    }
    public List<Property> findPropertiesByOwnerId(Integer ownerId) {
        return propertyRepository.findByOwner_OwnerId(ownerId);
    }
    

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }
    
    

    public Owner getOwnerById(Integer ownerId) {
        return ownerRepository.findById(ownerId).orElse(null);
    }
    public void deleteOwner(Integer ownerId) {
        ownerRepository.deleteById(ownerId);
    }
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }


  
}
