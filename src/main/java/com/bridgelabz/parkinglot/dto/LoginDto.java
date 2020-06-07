package com.bridgelabz.parkinglot.dto;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class LoginDto {
	
	
	@Column(nullable = false)
	public String password;
	
	
	@Pattern(regexp = ("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
	public String email;
	

}
