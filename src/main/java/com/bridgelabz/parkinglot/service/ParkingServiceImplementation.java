package com.bridgelabz.parkinglot.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.parkinglot.dto.ParkVehicleDTO;
import com.bridgelabz.parkinglot.model.Owner;
import com.bridgelabz.parkinglot.model.ParkedVehicle;
import com.bridgelabz.parkinglot.model.ParkingLot;
import com.bridgelabz.parkinglot.model.ParkingLotSystem;
import com.bridgelabz.parkinglot.repository.ParkedVehicleRepository;
import com.bridgelabz.parkinglot.repository.ParkingLotRepository;
import com.bridgelabz.parkinglot.repository.ParkingLotSystemRepository;
import com.bridgelabz.parkinglot.repository.OwnerRepository;
import com.bridgelabz.parkinglot.response.Response;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2

public class ParkingServiceImplementation implements IParkingService {

	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	ParkingLotSystemRepository parkingLotSystemRepository;
	@Autowired
	ParkingLotRepository parkingLotRepository;
	@Autowired
	ParkedVehicleRepository parkedVehicleRepository;

	@Override
	public Response parkVehicle(ParkVehicleDTO parkVehicleDTO) {
		ParkedVehicle parkedVehicle = new ParkedVehicle();
		BeanUtils.copyProperties(parkVehicleDTO,parkedVehicle);

		Owner owner = ownerRepository.findByEmailId(parkVehicleDTO.getEmailId());
		ParkingLot parkingLot = parkingLotAssigner(parkVehicleDTO);
		long allottedSlotNumber = slotAssigner(owner);
		if (parkingLot != null || parkingLot.getNumberOfVacantSlot() > 0 && checkVehicleAvailable(parkedVehicle.getNumberPlate())) {
			parkedVehicle.setAttendantName(parkingLot.getAttendantName());
			parkedVehicle.setSlotNumber(allottedSlotNumber);
			parkedVehicle.setParkingLot(parkingLot);
			parkingLot.addParkedVehicle(parkedVehicle);
			parkingLot.setNumberOfVacantSlot(parkingLot.getNumberOfVacantSlot() - 1);
			parkedVehicleRepository.save(parkedVehicle);
			parkingLotRepository.save(parkingLot);
			checkVaccant(parkingLot);
			log.info("Vehicle Parked "+parkedVehicle);
			return new Response("Vehicle Parked", 202);
		} else if (!checkVehicleAvailable(parkedVehicle.getNumberPlate()))
			return new Response("Vehicle already parked", 202);
		return new Response("All parkingLots are Full ", 202);
	}

	@Override
	public Response unParkVehicle(String numberPlate) {
		if (!checkVehicleAvailable(numberPlate)) {
			ParkedVehicle byNumberPlate = parkedVehicleRepository.findByNumberPlate(numberPlate);
			ParkingLot parkingLot = parkingLotRepository.findByAttendantName(byNumberPlate.getAttendantName());
			parkingLot.setNumberOfVacantSlot(parkingLot.getNumberOfVacantSlot() + 1);
			parkingLot.removeParkedVehicle(byNumberPlate);
			parkingLotRepository.save(parkingLot);
			checkVaccant(parkingLot);
			parkedVehicleRepository.delete(byNumberPlate);
			log.info("Vehicle unParked Successfully "+ byNumberPlate);
			return new Response("Vehicle unParked Successfully", 202);
		}
		return new Response("Vehicle is not parked here", 400);
	}

	private ParkingLot parkingLotAssigner(ParkVehicleDTO parkVehicleDTO) {
		List<ParkingLotSystem> list = parkingLotSystemRepository.findAllByOwner(ownerRepository.findByEmailId(parkVehicleDTO.getEmailId()));
		List<List<ParkingLot>> parkingLots = list.stream().map(ParkingLotSystem::getParkingLots).collect(Collectors.toList());
		//Class objectrefrence:map(y-> y.getParkingLots)
		//for(ParkingLotSystem parkingLotSystem : list){
		//	return  ParkingLotSystem.getParkingLots();
		//}
		long min = Integer.MAX_VALUE;
		int index = 0, i = 0;
		for (ParkingLot pls : parkingLots.get(0)) {
			if (min > pls.getNumberOfVacantSlot()) {

				min = pls.getNumberOfVacantSlot();
				index = i;
			}i++;
		}return parkingLots.get(0).get(index);
	}

	private boolean checkVehicleAvailable(String vehicleNumber) {
		return !parkedVehicleRepository.existsByNumberPlate(vehicleNumber);
	}


	private int slotAssigner(Owner owner) {
		List<ParkedVehicle> parkedVehicles = parkedVehicleRepository.findByEmailId(owner.getEmailId());
		List<Long> slot = parkedVehicles.stream().map(ParkedVehicle::getSlotNumber).sorted().collect(Collectors.toList());
		int defaultSlotValue = 1;
		for (long s : slot){
			if(defaultSlotValue < s) return defaultSlotValue;
			defaultSlotValue += 1;
		}
		return defaultSlotValue;
	}

	private void checkVaccant(ParkingLot parkingLot) {
		if(parkingLot.getNumberOfVacantSlot()==0) {
			parkingLot.setVacant(false);
			parkingLotRepository.save(parkingLot);
		}
		else if(parkingLot.getNumberOfVacantSlot() > 0) {
			parkingLot.setVacant(true);
			parkingLotRepository.save(parkingLot);

		}
	}
}