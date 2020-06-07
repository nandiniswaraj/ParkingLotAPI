package com.bridgelabz.parkinglot.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.parkinglot.configuration.Response;
import com.bridgelabz.parkinglot.dto.UserDto;
import com.bridgelabz.parkinglot.dto.LoginDto;
import com.bridgelabz.parkinglot.model.User;

@Service
public interface IUserService {

	String login(LoginDto loginDto);
	//Response authenticate(User person, String password);
	//Response validateEmail(String token);
	User registerUser(UserDto userDto);
	//User setPassword(LoginDto loginDto, String token);
	void verification(String token);
	
}
