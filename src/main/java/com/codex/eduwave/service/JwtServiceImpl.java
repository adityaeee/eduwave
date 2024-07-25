package com.codex.eduwave.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codex.eduwave.entity.Account;
import com.codex.eduwave.model.response.JwtClaims;
import com.codex.eduwave.service.intrface.AccountService;
import com.codex.eduwave.service.intrface.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class JwtServiceImpl implements JwtService {

    private final String JWT_SECRET;
    private final String ISSUER;
    private final long JWT_EXPIRATION;

    public JwtServiceImpl(
            @Value("${app.eduwave.jwt-secret}") String jwtSecret,
            @Value("${app.eduwave.jwt-app-name}") String issuer,
            @Value("${app.eduwave.jwt-expiration}") long expiration
    ) {
        JWT_SECRET = jwtSecret;
        ISSUER = issuer;
        JWT_EXPIRATION = expiration;
    }

    @Override
    public String generateToken(Account account) {
        try {
        Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(JWT_EXPIRATION))
                .withSubject(account.getId())
                .withClaim("roles", account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error while creating jwt token");
        }
    }

    @Override
    public boolean verifyJwtToken(String bearerToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            String token = parseJwt(bearerToken);
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    @Override
    public JwtClaims getClaimsByToken(String bearerToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT decodedJWT =verifier.verify(parseJwt(bearerToken));
            return JwtClaims.builder()
                    .accountId(decodedJWT.getSubject())
                    .roles(decodedJWT.getClaim("roles").asList(String.class))
                    .build();
        }catch (JWTVerificationException exception) {
            return null;
        }
    }

    private String parseJwt(String token) {
        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
