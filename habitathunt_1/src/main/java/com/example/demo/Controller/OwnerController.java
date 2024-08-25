package com.example.demo.Controller;

import com.example.demo.Modal.City;
import com.example.demo.Modal.Owner;
import com.example.demo.Modal.Property;
import com.example.demo.Repository.OwnerRepository;
import com.example.demo.Service.CityService;
import com.example.demo.Service.OwnerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "http://localhost:3000") 
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private  CityService cityService;
    
    

    @PostMapping("/login")	
    public ResponseEntity<Map<String, Object>> login(@RequestBody Owner loginRequest) {
    	 Owner owner = ownerService.findByOwnerEmail(loginRequest.getOwnerEmail());
    	    if (owner != null && owner.getOwnerPassword().equals(loginRequest.getOwnerPassword())) {
    	        Map<String, Object> response = new HashMap<>();
    	        response.put("message", "Login successful");
    	        response.put("ownerId", owner.getOwnerId());
    	        response.put("ownerName", owner.getOwnerName());
    	        return ResponseEntity.ok(response);
    	    } else {
    	        Map<String, Object> response = new HashMap<>();
    	        response.put("message", "Invalid email or password");
    	        return ResponseEntity.status(401).body(response);
    	    }
    }
    @GetMapping("/properties/{ownerId}")
    public ResponseEntity<List<Property>> getPropertiesByOwnerId(@PathVariable Integer ownerId) {
        List<Property> properties = ownerService.findPropertiesByOwnerId(ownerId);
        return ResponseEntity.ok(properties);
    }
    @GetMapping("/city/{ownerId}")
    public Owner getOwnerCity(@PathVariable Integer ownerId) {
        return ownerService.getOwnerById(ownerId);
    }
    @GetMapping("/getallowners")
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }
    @DeleteMapping("/delete/{ownerId}")
    public void deleteOwner(@PathVariable Integer ownerId) {
        ownerService.deleteOwner(ownerId);
    }
    @PostMapping("/add")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner newOwner = ownerService.saveOwner(owner);
        return new ResponseEntity<>(newOwner, HttpStatus.CREATED);
    }



    
}
