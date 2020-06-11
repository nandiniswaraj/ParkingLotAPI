package com.bridgelabz.parkinglot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.parkinglot.dto.ParkVehicleDTO;
import com.bridgelabz.parkinglot.response.Response;
import com.bridgelabz.parkinglot.service.IParkingService;
import com.bridgelabz.parkinglot.service.IOwnerService;

@RequestMapping(value = "/parking")
@RestController
public class ParkingController {
	
	
	@Autowired
    IOwnerService parkingService;

    @Autowired
    IParkingService parkingObserver;


	
	
	 @RequestMapping(value = "/parkVehicle", method = RequestMethod.POST)
	    public Response parkVehicle(@RequestBody ParkVehicleDTO parkVehicleDTO) {
	        return parkingObserver.parkVehicle(parkVehicleDTO);
	    }

	    @RequestMapping(value = "/unParkVehicle", method = RequestMethod.GET)
	    public Response parkVehicle(@RequestParam String vehicleNumber) {
	        return parkingObserver.unParkVehicle(vehicleNumber);
	    }
		
}


