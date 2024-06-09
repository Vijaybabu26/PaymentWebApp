package com.babu.fooddelivery.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.babu.fooddelivery.dto.UserDTO;
import com.babu.fooddelivery.entity.User;
import com.babu.fooddelivery.repository.UserRepo;


@Service
public class UserService {

	@Autowired
	UserRepo userrepo;

	 
	public String registeruser(UserDTO userdto) {
		
		User user = new User(
				userdto.getUserId(), 
				userdto.getUserName(),
				userdto.getPhoneNo(),
				userdto.getEmailId(),
				userdto.getPassword(),
				userdto.getAddress(), userdto.getRegistrationDate(), userdto.getProfilePicture()
		);
		
//		this.passwordencoder.encode(
		userrepo.save(user);
		return user.getUserName() +"Registration Successfull";
	}
//	
//	public boolean login(LoginDTO loginDTO) {
//        User user = userrepo.findByPhno(loginDTO.getPhoneNo());
//        if (user != null && loginDTO.getPassword().equals(user.getPassword())) {
//            return true;
//        }
//        return false;
//    }

}
