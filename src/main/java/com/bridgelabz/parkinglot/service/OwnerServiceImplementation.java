package com.bridgelabz.parkinglot.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.parkinglot.dto.CreateParkingLotDTO;
import com.bridgelabz.parkinglot.dto.LoginDTO;
import com.bridgelabz.parkinglot.dto.RegistrationDTO;
import com.bridgelabz.parkinglot.enumtype.EnumType;
import com.bridgelabz.parkinglot.model.Owner;
import com.bridgelabz.parkinglot.model.ParkingLot;
import com.bridgelabz.parkinglot.model.ParkingLotSystem;
import com.bridgelabz.parkinglot.repository.ParkingLotRepository;
import com.bridgelabz.parkinglot.repository.ParkingLotSystemRepository;
import com.bridgelabz.parkinglot.repository.OwnerRepository;
import com.bridgelabz.parkinglot.response.Response;
import com.bridgelabz.parkinglot.utility.EmailValidation;
import com.bridgelabz.parkinglot.utility.JwtToken;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OwnerServiceImplementation implements IOwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ParkingLotSystemRepository parkingLotSystemRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private EmailValidation emailValidation;
    @Autowired
    private JwtToken tokenGenerator;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Response registration(RegistrationDTO registrationDTO) {
    	Owner owner = new Owner();
  		BeanUtils.copyProperties(registrationDTO,owner);   
       if (ownerRepository.existsByEmailId(registrationDTO.getEmailId())) {
    	   log.info("User Already Exists");
   	        return new Response("User Already Exists", 404);
       }
         owner.setPassword(bCryptPasswordEncoder.encode(owner.getPassword()));
        ownerRepository.save(owner);
        return emailValidation.sendMail(owner, EnumType.REGISTRATION);
    }

    @Override
    public Response login(LoginDTO loginDTO) {
        Owner currentOwner = ownerRepository.findByEmailId(loginDTO.getEmail());
        if (currentOwner != null) {
            if (!currentOwner.isVerified()) return new Response("Not Verified Account, please verify first", 404);
            else if (bCryptPasswordEncoder.matches(loginDTO.getPassword(), currentOwner.getPassword()))
                return new Response("Login Successful", 222);
            else return new Response("Password not matched", 404);
        }
        return new Response("Owner Not Found", 404);
    }

    @Override
    public Response createParkingLotSystem(CreateParkingLotDTO createParkingLotDTO) {
        if (login(createParkingLotDTO.getLoginDTO()).getStatusCode() == 222) {
            for (int i = 0; i < createParkingLotDTO.getNoOfParkingLot(); i++) {
                ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
                parkingLotSystem.setAttendantName(createParkingLotDTO.getAttendant()[i]);
                parkingLotSystem.setTotalSlot(createParkingLotDTO.getSlotSize()[i]);
                Owner owner = ownerRepository.findByEmailId(createParkingLotDTO.getLoginDTO().getEmail());
                owner.addParkingLotSystems(parkingLotSystem);
                ownerRepository.save(owner);
                parkingLotSystem.setOwner(owner);
                parkingLotSystemRepository.save(parkingLotSystem);
                ParkingLot parkingLot = new ParkingLot();
                parkingLot.setAttendantName(createParkingLotDTO.getAttendant()[i]);
                parkingLot.setVacant(true);
                parkingLot.setSizeOfSlot(createParkingLotDTO.getSlotSize()[i]);
                parkingLot.setNumberOfVacantSlot(createParkingLotDTO.getSlotSize()[i]);
                parkingLot.setParkingLotSystem(parkingLotSystem);
                parkingLot.setOwner(owner);
                parkingLotRepository.save(parkingLot);
                parkingLotSystem.addParkingLotSystem(parkingLot);
            }
            updateDatabase(createParkingLotDTO);
            return new Response("Parking Lot System Created Successful", 202);
        }
        return new Response("Owner not found or Unverified Owner", 406);
    }

    private void updateDatabase(CreateParkingLotDTO createParkingLot) {
        Owner owner = ownerRepository.findByEmailId(createParkingLot.getLoginDTO().getEmail());
        owner.setNumberOfParkingLotSystem(parkingLotSystemRepository.findAllByOwner(owner).size());
        ownerRepository.save(owner);
    }

    @Override
    public Response verifyAccount(String token) {
        String mail = tokenGenerator.decodeToken(token);
        Owner owner = ownerRepository.findByEmailId(mail);
        owner.setVerified(true);
        ownerRepository.save(owner);
        return new Response("Hello  " + owner.getFirstName() + " ,Account verified successfully", 202);
    }
}
