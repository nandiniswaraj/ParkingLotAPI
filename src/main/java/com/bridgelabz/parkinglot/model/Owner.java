package com.bridgelabz.parkinglot.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@Entity
@ToString

public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ownerId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String mobileNumber;
    
    private int numberOfParkingLotSystem;

    private Date date = new Date();

    private boolean isVerified;

    @OneToMany(mappedBy = "owner")
    public List<ParkingLotSystem> parkingLotSystems = new ArrayList<>();

    @OneToMany
    public List<ParkingLot> parkingLots = new ArrayList<>();

    public void addParkingLotSystems(ParkingLotSystem parkingLotSystems) {
        this.parkingLotSystems.add(parkingLotSystems);
    }

    public void addParkingLots(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }
}