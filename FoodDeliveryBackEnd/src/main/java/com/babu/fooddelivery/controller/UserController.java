package com.babu.fooddelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babu.fooddelivery.dto.LoginDTO;
import com.babu.fooddelivery.dto.UserDTO;
import com.babu.fooddelivery.repository.UserRepo;
import com.babu.fooddelivery.service.UserService;





@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userser;
	
	@Autowired
	UserRepo userrepo;
	
	@PostMapping("/registeruser")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
//		String phoneNo = user.getPhoneNo();
//	    if (userrepo.findByPhno(phoneNo) != null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
//	    }
	    
	    if (userser.registeruser(user) != null) {
	        String message = "Registration Successful";
	        System.out.println(user);
	        return ResponseEntity.ok(user.getUserName() + message);
	    }
	    
	    return ResponseEntity.ok("Registration Failed");
	}
	
//	@PostMapping("/loginuser")
//    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
//        boolean isAuthenticated = userser.login(loginDTO);
//
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login Successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
//        }
//    }
}
