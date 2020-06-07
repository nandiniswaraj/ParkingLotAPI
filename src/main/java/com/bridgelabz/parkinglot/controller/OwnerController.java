package com.bridgelabz.parkinglot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.parkinglot.configuration.Response;
import com.bridgelabz.parkinglot.dto.VehicleDto;
import com.bridgelabz.parkinglot.service.IOwnerService;

public class OwnerController {
	
	@Autowired
	private IOwnerService ownerService;

	@PostMapping("/createparking")
	public Response createParking(@RequestParam("parkingLotCapacity") Integer parkingLotCapacity) {
		return ownerService.isParkingLotCreate(parkingLotCapacity);

	}

	
	
		
}
