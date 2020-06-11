package com.bridgelabz.parkinglot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.parkinglot.dto.CreateParkingLotDTO;
import com.bridgelabz.parkinglot.dto.LoginDTO;
import com.bridgelabz.parkinglot.dto.RegistrationDTO;
import com.bridgelabz.parkinglot.response.Response;
import com.bridgelabz.parkinglot.service.IParkingService;
import com.bridgelabz.parkinglot.service.IOwnerService;

@RestController
@RequestMapping("/parkinglotStstem")

public class OwnerController {

    @Autowired
    private IOwnerService ownerService;

   
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Response registration(@RequestBody RegistrationDTO registrationDTO) {
        return ownerService.registration(registrationDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody LoginDTO loginDTO) {
        return ownerService.login(loginDTO);
    }

    @RequestMapping(value = "/verifyemail/{token}", method = RequestMethod.GET)
    public Response verifyEmail(@PathVariable String token) {
        return ownerService.verifyAccount(token);
    }

    @RequestMapping(value = "/CreateParkingLot", method = RequestMethod.POST)
    public Response createParkingLotSystem(@RequestBody CreateParkingLotDTO createParkingLot) {
        return ownerService.createParkingLotSystem(createParkingLot);
    }
}
