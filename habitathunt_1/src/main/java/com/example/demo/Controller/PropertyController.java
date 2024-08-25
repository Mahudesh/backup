package com.example.demo.Controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Modal.City;
import com.example.demo.Modal.Owner;
import com.example.demo.Modal.Property;
import com.example.demo.Service.PropertyService;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    
//    @CrossOrigin(origins = "http://localhost:3000") 
//    @GetMapping("/getall")
//    public List<Property> getAllProperties() {
//        return propertyService.getAllProperties();
//    }
//    @CrossOrigin(origins = "http://localhost:3000")
//    @GetMapping("/city/{cityId}")
//    public List<Property> getPropertiesByCityId(@PathVariable Integer cityId) {
//        return propertyService.getPropertiesByCityId(cityId);
//    }
//    @CrossOrigin(origins = "http://localhost:3000") 
//    @GetMapping("/cbe/{id}")
//    public Property getPropertyById(@PathVariable Integer id) {
//        return propertyService.getPropertyById(id);
//    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/city/getall/{cityId}")
    public List<Property> getPropertiesByCity(@PathVariable("cityId") Integer cityId) {
        return propertyService.getPropertiesByCity(cityId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/city/{cityId}/{propertyId}")
    public Property getPropertyById(@PathVariable Integer cityId, @PathVariable Integer propertyId) {
        return propertyService.getPropertyByCityAndId(cityId, propertyId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer propertyId) {
        propertyService.deletePropertyById(propertyId);
        return ResponseEntity.noContent().build();
    }
//	    @CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public ResponseEntity<?> addProperty(
        @RequestParam("city") Integer cityId,
        @RequestParam("owner") Integer ownerId,
        @RequestParam("price") Double price,
        @RequestParam("bhk") Integer bhk,
        @RequestParam("squareFeet") Integer squareFeet,
        @RequestParam("furnish") String furnish,
        @RequestParam("availableFor") String availableFor,
        @RequestParam("villageName") String villageName,
        @RequestParam("district") String district,
        @RequestParam("maintenance") String maintenance,
        @RequestParam("balcony") String balcony,
        @RequestParam("leaseType") String leaseType,
        @RequestParam("parking") String parking,
        @RequestParam("carpetArea") String carpetArea,
        @RequestParam("brokerage") String brokerage,
        @RequestParam("bathrooms") Integer bathrooms,
        @RequestParam("gaspipeline") String gaspipeline,
        @RequestParam("priceneg") String priceneg,
        @RequestParam("flooring") String flooring,
        @RequestParam("about") String about,
        @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            byte[] imageBytes = imageFile.getBytes();

            Property property = new Property();
            property.setCity(new City(cityId));
            property.setOwner(new Owner(ownerId));
            property.setPrice(price);
            property.setBhk(bhk);
            property.setSquareFeet(squareFeet);
            property.setFurnish(furnish);
            property.setAvailableFor(availableFor);
            property.setVillageName(villageName);
            property.setDistrict(district);
            property.setMaintenance(maintenance);
            property.setBalcony(balcony);
            property.setLeaseType(leaseType);
            property.setParking(parking);
            property.setCarpetArea(carpetArea);
            property.setBrokerage(brokerage);
            property.setBathrooms(bathrooms);
            property.setGaspipeline(gaspipeline);
            property.setPriceneg(priceneg);
            property.setFlooring(flooring);
            property.setAbout(about);
            property.setImage(imageBytes);

            propertyService.saveProperty(property);
            return ResponseEntity.ok("Property added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding property: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/property-image/{propertyId}")
    public ResponseEntity<String> getPropertyImage(@PathVariable Integer propertyId) {
        Optional<Property> property = propertyService.findById(propertyId);

        if (property.isPresent()) {
            byte[] image = property.get().getImage(); 
            String base64Image = Base64.getEncoder().encodeToString(image);
            return ResponseEntity.ok(base64Image);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }
    }

}