package com.bridgelabz.parkinglot.utility;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Service;

@Service
public class JwtToken {
    public final String TOKEN_SECRET = "sd5745FAHFW";

    public String generateToken(String emailId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create().withClaim("emailId", emailId).sign(algorithm);
            return token;
        } catch (JWTCreationException | IllegalArgumentException exception) {
            exception.getMessage();
        }
        return null;
    }

    public String decodeToken(String token) {
        Verification verification = null;
        try {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        com.auth0.jwt.JWTVerifier jwtverifier = verification.build();
        DecodedJWT decodedjwt = jwtverifier.verify(token);
        Claim claim = decodedjwt.getClaim("emailId");
        return claim.asString();
    }
}
