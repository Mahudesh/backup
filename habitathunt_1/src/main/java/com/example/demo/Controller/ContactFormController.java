package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Modal.ContactForm;
import com.example.demo.Modal.SignupData;
import com.example.demo.Service.ContactFormService;

@RestController
@RequestMapping("/api/contact")
public class ContactFormController 
{
	

	    @Autowired
	    private ContactFormService contactFormService;

	    @GetMapping("/user/{userId}")
	    public SignupData getUserDetails(@PathVariable int userId) {
	        return contactFormService.getUserDetails(userId);
	    }

	    @PostMapping("/contact")
	    public ContactForm submitContactForm(@RequestBody ContactForm contactForm) {
	        return contactFormService.submitContactForm(contactForm);
	    }
	
}
