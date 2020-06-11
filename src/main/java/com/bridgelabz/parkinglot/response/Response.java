package com.bridgelabz.parkinglot.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    public String message;
    public int statusCode;

    public Response(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
