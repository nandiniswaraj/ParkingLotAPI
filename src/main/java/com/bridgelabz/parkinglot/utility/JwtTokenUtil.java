package com.bridgelabz.parkinglot.utility;

import java.sql.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("serial")
@Component
public class JwtTokenUtil {

	private static final String SECRET = "9876543210";

	public String createToken(long l) {
		String token = null;
		try {
			token = JWT.create().withClaim("id", l).sign(Algorithm.HMAC512(SECRET));
		} catch (Exception e) {

		}
		return token;
	}

	public static int decodeToken(String token) {
		int id = 0;
		if (token != null) {
				id=JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token).getClaim("id").asInt();
		}
		return id;
	}

	 
  
}
