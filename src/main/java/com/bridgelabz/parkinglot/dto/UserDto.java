package com.bridgelabz.parkinglot.dto;
import javax.validation.constraints.Pattern;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDto {
	
	private String firstName;
    private String lastName;
    @Pattern(regexp = ("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
	//@Email(message = "Please Enter Valid Email")
	//(message = "Please Enter Email")
	public String email;
    
	public String password;
    private String mobileNumber;
    private String role;
   

    

}
