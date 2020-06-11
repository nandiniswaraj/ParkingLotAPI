package com.bridgelabz.parkinglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.parkinglot.model.Owner;
import com.bridgelabz.parkinglot.model.ParkingLotSystem;

import java.util.List;

public interface ParkingLotSystemRepository extends JpaRepository<ParkingLotSystem, Long> {

    List<ParkingLotSystem> findAllByOwner(Owner owner);
}