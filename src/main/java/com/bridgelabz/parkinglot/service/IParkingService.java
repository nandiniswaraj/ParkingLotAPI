package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.dto.ParkVehicleDTO;
import com.bridgelabz.parkinglot.response.Response;

public interface IParkingService {

    Response parkVehicle(ParkVehicleDTO parkVehicleDTO);

    Response unParkVehicle(String vehicleNumber);
}
