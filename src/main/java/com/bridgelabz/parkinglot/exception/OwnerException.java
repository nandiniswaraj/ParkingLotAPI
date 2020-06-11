package com.bridgelabz.parkinglot.exception;

import com.bridgelabz.parkinglot.exception.UserException.exceptionType;

public class OwnerException extends Exception {
	
	
	 private static final long serialVersionUID = 1L;
	    public exceptionType type;

	    public OwnerException(exceptionType type) {
	        this.type = type;
	    }

	    public enum exceptionType {
	        OWNER_NOT_FOUND,
	        INVALID_EMAIL_ID,
	        INVALID_PASSWORD,
	        OWNER_ALREADY_EXIST,
	        EMAIL_ALREADY_VERIFIED;
	        
	    }


}
