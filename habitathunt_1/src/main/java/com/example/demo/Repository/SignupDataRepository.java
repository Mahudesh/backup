package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modal.SignupData;

@Repository
public interface SignupDataRepository extends JpaRepository<SignupData, Integer> {
    
	    SignupData findByEmailId(String emailId);
	    SignupData findByCustomerId(int customerId);
	}