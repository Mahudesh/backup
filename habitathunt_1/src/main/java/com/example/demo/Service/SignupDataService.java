package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Modal.SignupData;
import com.example.demo.Repository.SignupDataRepository;

@Service
public class SignupDataService {

    @Autowired
    private SignupDataRepository signupDataRepository;

   
    public SignupData saveUser(SignupData user) {
        return signupDataRepository.save(user);
    }

    
    public SignupData getUserByEmail(String email) {
        return signupDataRepository.findByEmailId(email);
    }
    public List<SignupData> getAllUsers() {
        return signupDataRepository.findAll();
    }		

	public void deleteById(int id) {
		
		signupDataRepository.deleteById(id);
		
	}
	  public SignupData updateUser(int customerId, SignupData updatedUser) {
	        SignupData existingUser = signupDataRepository.findById(customerId).orElseThrow();
	        existingUser.setUserName(updatedUser.getUserName());
	        existingUser.setEmailId(updatedUser.getEmailId());
	        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
	        return signupDataRepository.save(existingUser);
	    }
}