package com.cybersoft.cozastore_java21.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.payload.request.SignupRequest;
import com.cybersoft.cozastore_java21.repository.UserRespository;

@Service
public class UserService implements UserServiceImp{

	@Autowired
	private UserRespository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean addUser(SignupRequest request) {
		boolean isSuccess = false;
		try {
			UserEntity user = new UserEntity();
			user.setUsername(request.getUsername());
			user.setEmail(request.getEmail());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			userRepository.save(user);
			isSuccess = true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return isSuccess;
	}
	
}
