package com.bridgelabz.parkinglot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.parkinglot.configuration.ApplicationConfig;
import com.bridgelabz.parkinglot.dto.LoginDto;
import com.bridgelabz.parkinglot.dto.UserDto;
import com.bridgelabz.parkinglot.model.User;
import com.bridgelabz.parkinglot.response.UserResponseDTO;
import com.bridgelabz.parkinglot.service.IUserService;


@RequestMapping(value = "/user")
@RestController
public class UserController {

	    @Autowired
	    private IUserService userService;

	     
	    @PostMapping("/registration")
	    @ApiOperation("User Register API")
	    public ResponseEntity<UserResponseDTO> registration(@RequestBody  UserDto userDto) {
	        User user = userService.registerUser(userDto);
	        return new ResponseEntity<>(new UserResponseDTO(user,
	                ApplicationConfig.getMessageAccessor().getMessage("101")), HttpStatus.CREATED);
	    }
	    
	    @PostMapping("/verifyEmail")
	    @ApiOperation("email verification API")
	    public ResponseEntity<UserResponseDTO> verifyEmail(@RequestHeader String token) {
	        userService.verification(token);
	        return new ResponseEntity<>(new UserResponseDTO(token,
	                ApplicationConfig.getMessageAccessor().getMessage("107")), HttpStatus.OK);
	    }
	   
	    @PostMapping("/login")
	    @ApiOperation("User Login API")
	    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDto loginDto) {
	        String user = userService.login(loginDto);
	        return new ResponseEntity<>(new UserResponseDTO(user,
	                ApplicationConfig.getMessageAccessor().getMessage("102")), HttpStatus.OK);
	    }

	 
	  	   
}
