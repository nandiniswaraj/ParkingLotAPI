package com.bridgelabz.parkinglot.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegistrationDTO {

    @Pattern(regexp = "[a-zA-Z]+([.|_][a-zA-Z0-9]+)?@([a-zA-Z]{3})(.)([a-zA-Z]{2})(.[a-zA-Z][a-zA-Z])?", message = "Type Valid Email Id")
    private String emailId;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "length should be minimum 8")
    private String password;

    @Pattern(regexp = "[9876][0-9]{9}", message = "enter valid mobile number")
    private String mobileNumber;

    @Pattern(regexp = "^[a-zA-Z]{3}", message = "length must be 3")
    private String firstName;

    private String lastName;

   
}
