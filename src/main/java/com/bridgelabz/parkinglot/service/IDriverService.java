package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.dto.VehicleDto;
import org.springframework.http.ResponseEntity;

import com.bridgelabz.parkinglot.configuration.Response;

import com.bridgelabz.parkinglot.exception.ParkingLotException;

public interface IDriverService {
	

	Response parkVehicle(VehicleDto vehicleDto, String token) throws ParkingLotException;

	Response unParkVehicle(String registrationNo) throws ParkingLotException;


}
