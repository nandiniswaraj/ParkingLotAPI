package com.bridgelabz.parkinglot.utility;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgelabz.parkinglot.exception.UserException;
import com.bridgelabz.parkinglot.model.User;
import com.bridgelabz.parkinglot.repository.IUserRepository;

@Component
public class Utility {
	
	    @Autowired
	    private IUserRepository userRepository;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	  /*  @Autowired
	    private RabbitMqDto rabbitMqDto;

	    @Autowired
	    private RabbitMqImp rabbitMq;

	
	 
	    public Long validateEmail(String email,String sub) {
	    	
	        return  userRepository.findByEmail(email)
	                             .map(user -> {
	                              String token = jwtTokenUtil.createToken(user.getParkingId());
	                              String body =   token ;
	                              System.out.println("token = " +token);
	                              rabbitMqDto.setTo(email);
	                              rabbitMqDto.setFrom("nandiniswaraj95@gmail.com");
	                              rabbitMqDto.setSubject(sub);
	                              rabbitMqDto.setBody(body);
	                              rabbitMq.sendMessageToQueue(rabbitMqDto);
	                              rabbitMq.send(rabbitMqDto);
	                              return user.getParkingId();
	                               })
	                      .orElseThrow(() -> new UserException(UserException.exceptionType.INVALID_EMAIL_ID));
	    }*/

	 
	   
	    public Boolean validateUser(String email) {
	       Optional<User> byEmail = userRepository.findByEmail(email);
	        if (byEmail.isPresent()) 
	            return true;
	        return false;
	    }
	    
	    

}
