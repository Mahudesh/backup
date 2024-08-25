package com.example.demo.Repository;

import com.example.demo.Modal.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {
}
