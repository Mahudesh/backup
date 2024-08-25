package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Modal.SignupData;
import com.example.demo.Service.SignupDataService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") 
public class AuthController {
	

    @Autowired
    private SignupDataService signupDataService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupData signupData) {
        SignupData existingUser = signupDataService.getUserByEmail(signupData.getEmailId());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        signupDataService.saveUser(signupData);
        return ResponseEntity.ok("Signup successful!");
    }

  
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody SignupData loginData)
//    		{
//    	 if (loginData.getEmailId().equals("admin@gmail.com") && loginData.getPassword().equals("admin123")) {
//             return ResponseEntity.ok("Admin");
//         }
//        SignupData user = signupDataService.getUserByEmail(loginData.getEmailId());
//        if (user == null || !user.getPassword().equals(loginData.getPassword())) {
//            return ResponseEntity.badRequest().body("Invalid email or password!");
//        }
//       
//        return ResponseEntity.ok("Login successful!");
//    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignupData loginData) {
        if (loginData.getEmailId().equals("admin@gmail.com") && loginData.getPassword().equals("admin123")) {
            return ResponseEntity.ok(Map.of("message", "Admin", "userId", "admin"));
        }

        SignupData user = signupDataService.getUserByEmail(loginData.getEmailId());
        if (user == null || !user.getPassword().equals(loginData.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid email or password!"));
        }

       
        return ResponseEntity.ok(Map.of("message", "Login successful!", "userId", user.getCustomerId()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody SignupData resetData) {
        SignupData user = signupDataService.getUserByEmail(resetData.getEmailId());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email address!");
        }
        user.setPassword(resetData.getPassword());
        signupDataService.saveUser(user);
        return ResponseEntity.ok("Password update successful!");
    }
    @GetMapping("/users")
    public List<SignupData> getAllUsers() {
        return signupDataService.getAllUsers();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        signupDataService.deleteById(id);
    }
    @PutMapping("/update/{customerId}")
    public SignupData updateUser(@PathVariable int customerId, @RequestBody SignupData updatedUser) {
        return signupDataService.updateUser(customerId, updatedUser);
    }
    
}