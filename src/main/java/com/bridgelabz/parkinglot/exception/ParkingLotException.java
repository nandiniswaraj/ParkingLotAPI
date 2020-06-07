package com.bridgelabz.parkinglot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ParkingLotException  extends Exception  {

	
		private static final long serialVersionUID = 1L;
		int code;
		String msg;

		public ParkingLotException() {
		}

		public ParkingLotException(int code, String msg) {
			super(msg);
			this.code = code;

		}

		public ParkingLotException(String msg) {
			super(msg); 
		}

		@Override
		public String toString() {
			return "UserException [code=" + code + ", msg=" + msg + "]";
		}

	}

