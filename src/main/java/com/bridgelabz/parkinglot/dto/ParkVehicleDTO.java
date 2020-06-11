package com.bridgelabz.parkinglot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkVehicleDTO {
    private String emailId;
    private String driverName;
    private String driverType;

    private String vehicleModel;
    private String vehicleColor;
    private String vehicleType;
    private String numberPlate;
}
