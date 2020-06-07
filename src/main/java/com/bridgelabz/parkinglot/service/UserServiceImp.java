package com.bridgelabz.parkinglot.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.parkinglot.utility.EmailGenerator;
import com.bridgelabz.parkinglot.dto.LoginDto;
import com.bridgelabz.parkinglot.dto.UserDto;
import com.bridgelabz.parkinglot.exception.UserException;
import com.bridgelabz.parkinglot.model.User;
import com.bridgelabz.parkinglot.repository.IUserRepository;
import com.bridgelabz.parkinglot.response.UserResponseDTO;
import com.bridgelabz.parkinglot.utility.JwtTokenUtil;
import com.bridgelabz.parkinglot.utility.Utility;

@Service
public class UserServiceImp implements IUserService {
	 
	
	@Autowired
	private Utility utility;
	@Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtTokenUtil generateToken;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	private EmailGenerator emailGenerate;

    
    @Override
    public User registerUser(UserDto userDto) {
        if (utility.validateUser(userDto.email)) {
            throw new UserException(UserException.exceptionType.USER_ALREADY_EXIST);
        }
        User user = new User();
		BeanUtils.copyProperties(userDto, user);
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setRegDate(localDateTime);
        user.setVerify(false);
        user.setPassword(passwordEncoder.encode(userDto.password));
         // String token =  generateToken.createToken(user.getUserId());
        userRepository.save(user);
        
          String token = "http://192.168.1.175:4200/active/" + generateToken.createToken(user.getUserId());
      	
			
			emailGenerate.sendEmail(user.getEmail(), "User Varification", token);
			System.out.println("MAil sent to user mailId");
			System.out.println(token);
	       
        return userRepository.save(user);
    }

    @Override
    public String login(LoginDto loginDto) {
    	User user = new User();
         user = userRepository.findByEmail(loginDto.email)
             .orElseThrow(() -> new UserException(UserException.exceptionType.INVALID_EMAIL_ID));
        if (!passwordEncoder.matches(loginDto.password, user.getPassword()))
            throw new UserException(UserException.exceptionType.INVALID_PASSWORD);
        
           String token = generateToken.createToken(user.getUserId());
         
           return token;
           
    }
	

    
    @Override
    public void verification(String token) {
    	long id = generateToken.decodeToken(token);
    	System.out.println(id);
    		  userRepository.findById(id)
                .map(user -> {
                	if(user.isVerify()) {
               throw new UserException(UserException.exceptionType.EMAIL_ALREADY_VERIFIED);
                	}
                		user.setVerify(true);
                 return user;
                }
                )
                .map(userRepository::save).get();
                }
    	
    	
    
   
}

