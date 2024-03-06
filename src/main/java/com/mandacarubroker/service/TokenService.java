package com.mandacarubroker.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mandacarubroker.model.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  public String generateToken(User user){
    return JWT.create()
        .withIssuer("Stocks")
        .withSubject(user.getUsername())
        .withClaim("id",user.getId())
        .withExpiresAt(LocalDateTime.now()
            .plusMinutes(60)
            .toInstant(ZoneOffset.of("-03:00")))
        .sign(Algorithm.HMAC256("shhhhhiii"));

  }
}
