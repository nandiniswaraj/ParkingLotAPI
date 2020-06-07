package com.bridgelabz.parkinglot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.parkinglot.ParkingLotApiApplication;
import com.bridgelabz.parkinglot.configuration.Response;
import com.bridgelabz.parkinglot.dto.VehicleDto;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.response.VehicleResponseDto;
import com.bridgelabz.parkinglot.service.IDriverService;

public class ParkingController {

	
	
	@Autowired
	private IDriverService driverService;

	@PostMapping("/park")
	public Response  Park(@RequestParam("token") String token,@RequestBody VehicleDto vehicleDTO) throws ParkingLotException {
		return driverService.parkVehicle(vehicleDTO, token);
	}

	@DeleteMapping("/unpark")
	public Response  unPark(@RequestParam("registrationNo") String registrationNo)
			throws ParkingLotException {
		return driverService.unParkVehicle(registrationNo);
	}

		
}


