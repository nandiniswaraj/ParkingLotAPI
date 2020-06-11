package com.bridgelabz.parkinglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.parkinglot.model.Owner;
import com.bridgelabz.parkinglot.model.ParkingLot;

import java.util.List;


public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    List<ParkingLot> findAllByOwner(Owner owner);
    ParkingLot findByAttendantName(String attendantName);
}
