package com.bridgelabz.parkinglot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParkingLotDTO {
    private LoginDTO loginDTO;
    private int noOfParkingLot;
    private String[] attendant;
    private Integer[] slotSize;
}
