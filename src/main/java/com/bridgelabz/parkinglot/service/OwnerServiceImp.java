package com.bridgelabz.parkinglot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.bridgelabz.parkinglot.configuration.Response;
import com.bridgelabz.parkinglot.exception.ResponseHelper;

public class OwnerServiceImp implements IOwnerService{
	
	@Autowired
	private Environment environment;

	


	@Override
	public Response isParkingLotCreate(Integer parkingLotCapacity) {
		for (int i = 0; i < parkingLotCapacity; i++) {
			DriverServiceImp.AVAILABLEPARKINGLOTS.add(i + 1);
		}

		System.out.println(DriverServiceImp.AVAILABLEPARKINGLOTS);
		 return ResponseHelper.statusResponse(200, environment.getProperty("status.parkinglot.createdSuccessfull"),
					parkingLotCapacity);
	}




}
