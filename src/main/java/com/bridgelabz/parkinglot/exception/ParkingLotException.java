package com.bridgelabz.parkinglot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.bridgelabz.parkinglot.exception.UserException.exceptionType;

@ResponseStatus
public class ParkingLotException  extends Exception  {

	
	  private static final long serialVersionUID = 1L;
	    public exceptionType type;

	    public ParkingLotException(exceptionType type) {
	        this.type = type;
	    }

	    public enum exceptionType {
	        USER_NOT_FOUND,
	        VEHICLE_NOT_EXIST,
	        PARKINGLOT_FULL,
	        DRIVER_NOT_FOUND;
	        
	    }
	}

