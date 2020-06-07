package com.bridgelabz.parkinglot.service;

import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.bridgelabz.parkinglot.dto.VehicleDto;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.exception.ResponseHelper;
import com.bridgelabz.parkinglot.exception.UserException;
import com.bridgelabz.parkinglot.model.User;
import com.bridgelabz.parkinglot.model.Vehicle;
import com.bridgelabz.parkinglot.repository.IParkingSlotRepository;
import com.bridgelabz.parkinglot.repository.IUserRepository;
import com.bridgelabz.parkinglot.response.UserResponseDTO;
import com.bridgelabz.parkinglot.utility.JwtTokenUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bridgelabz.parkinglot.configuration.ApplicationConfig;
import com.bridgelabz.parkinglot.configuration.Response;

public class DriverServiceImp implements IDriverService{
	

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IParkingSlotRepository parkingRepository;
	
	@Autowired
	private Environment environment;


	protected static final TreeSet<Integer> AVAILABLEPARKINGLOTS = new TreeSet<>();

	@Override
	public Response parkVehicle(VehicleDto vehicleDto, String token) throws ParkingLotException {
		Integer slotNo;
		if (AVAILABLEPARKINGLOTS.isEmpty()) {
			throw new ParkingLotException(environment.getProperty("status.parkinglot.full"));
			}
		slotNo = AVAILABLEPARKINGLOTS.pollFirst();
		System.out.println("slotNo: " + slotNo);
		long id = JwtTokenUtil.decodeToken(token);
		Optional<User> idAvailable = userRepository.findById(id);
		if (idAvailable == null) {
			
	 return ResponseHelper.statusResponse(200, environment.getProperty("status.parkinglot.NOT_FOUND_RESPONSE_CODE"),
						"");

		}
		Vehicle vehicle = new Vehicle();
		vehicle.setRegistrationNo(vehicleDto.getRegistrationNo());

		vehicle.setVehicleColor(vehicleDto.getVehicleColor());
		vehicle.setSlotNo(slotNo);
		vehicle.setVehicleUser(idAvailable);
		parkingRepository.save(vehicle);
		
		 return ResponseHelper.statusResponse(200, environment.getProperty("status.parkinglot.park"),
					vehicle);
	
	}

	@Override
	public Response unParkVehicle(String registrationNo) throws ParkingLotException {
	    Optional< Vehicle> vehicle = parkingRepository.findVehicle(registrationNo);
		if (vehicle == null) {
			throw new ParkingLotException(environment.getProperty("status.parkinglot.vehiclenotexist"));
			
		}
		System.out.println(AVAILABLEPARKINGLOTS);
		Integer slotNo = vehicle.get().getSlotNo();
		AVAILABLEPARKINGLOTS.add(slotNo);
		System.out.println(AVAILABLEPARKINGLOTS);
		System.out.println(slotNo);
		parkingRepository.delete(vehicle);
		 return ResponseHelper.statusResponse(200, environment.getProperty("status.parkinglot.unpark"),
					vehicle);
	
	}


	
	}
