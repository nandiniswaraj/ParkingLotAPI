package com.bridgelabz.parkinglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.parkinglot.model.ParkedVehicle;

import java.util.List;

public interface ParkedVehicleRepository extends JpaRepository<ParkedVehicle, Long> {
    boolean existsByNumberPlate(String numberPlate);

    ParkedVehicle findByNumberPlate(String numberPlate);

    List<ParkedVehicle> findByEmailId(String emailId);

}
