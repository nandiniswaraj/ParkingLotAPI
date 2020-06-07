package com.bridgelabz.parkinglot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.parkinglot.model.Vehicle;

@Repository

public interface IParkingSlotRepository extends JpaRepository<Vehicle, Long> {

	Optional<Vehicle> findVehicle(String vehicleNo);
	
	List<Vehicle> findByVehicle(String vehicleNo);
	

	List<Vehicle>findByColor(String vehicleColor);

	void delete(Optional<Vehicle> vehicle);
	
	

}