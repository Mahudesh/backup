package com.example.demo.Service;

import com.example.demo.Modal.ContactForm;
import com.example.demo.Modal.SignupData;
import com.example.demo.Repository.ContactFormRepository;
import com.example.demo.Repository.SignupDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactFormService {

    @Autowired
    private ContactFormRepository contactFormRepository;

    @Autowired
    private SignupDataRepository signupDataRepository;

    public SignupData getUserDetails(int userId) {
        return signupDataRepository.findByCustomerId(userId);
    }

    public ContactForm submitContactForm(ContactForm contactForm) {
        return contactFormRepository.save(contactForm);
    }
}
