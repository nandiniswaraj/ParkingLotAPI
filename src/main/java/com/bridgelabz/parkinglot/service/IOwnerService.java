package com.bridgelabz.parkinglot.service;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.parkinglot.configuration.Response;
import com.bridgelabz.parkinglot.exception.ParkingLotException;

public interface IOwnerService {
	
	
	Response isParkingLotCreate(Integer parkingLotCapacity);



}
