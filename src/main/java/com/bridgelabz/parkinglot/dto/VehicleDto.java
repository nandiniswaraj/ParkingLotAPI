package com.bridgelabz.parkinglot.dto;

import java.util.Optional;

import com.bridgelabz.parkinglot.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VehicleDto {
	
	private String registrationNo;
	private String vehicleColor;

	

}
