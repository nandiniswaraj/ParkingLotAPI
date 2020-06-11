package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.dto.CreateParkingLotDTO;
import com.bridgelabz.parkinglot.dto.LoginDTO;
import com.bridgelabz.parkinglot.dto.RegistrationDTO;
import com.bridgelabz.parkinglot.response.Response;

public interface IOwnerService {

    Response registration(RegistrationDTO user);

    Response verifyAccount(String token);

    Response login(LoginDTO loginDTO);

    Response createParkingLotSystem(CreateParkingLotDTO createParkingLotDTO);
}
