package com.bridgelabz.parkinglot.response;

public class UserResponseDTO {

    public Object data;
    public String message;

    public UserResponseDTO(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
