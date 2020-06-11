package com.bridgelabz.parkinglot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity

public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long parkingLotId;

    private String attendantName;
    private long sizeOfSlot;
    private long numberOfVacantSlot;
    private boolean isVacant;

   
    @OneToMany(mappedBy = "parkingLot")
    private List<ParkedVehicle> parkedVehicleList = new ArrayList<>();

    @ManyToOne
    private ParkingLotSystem parkingLotSystem;

    @ManyToOne
    private Owner owner;

    public void addParkedVehicle(ParkedVehicle parkedVehicle) {
        this.parkedVehicleList.add(parkedVehicle);
    }

    public void removeParkedVehicle(ParkedVehicle parkedVehicle) {
        this.parkedVehicleList.remove(parkedVehicle);
    }

}
