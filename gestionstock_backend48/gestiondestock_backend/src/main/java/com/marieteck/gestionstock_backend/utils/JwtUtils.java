package com.marieteck.gestionstock_backend.utils;


import com.marieteck.gestionstock_backend.dto.auth.ExtendedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {
    // here we'll create all the needs methods to manipulate application token

    // let'us take the values in applications properties

    @Value("${app.secret-key}")
    private String secretKey;


    // function to generateToken
    public String generateToken(ExtendedUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
       // claims.put("idEnterprise",String.valueOf(userDetails.getIdEntreprise()));
        return createToken(claims,userDetails);
    }


    // function to createToken

    private String createToken(Map<String, Object> claims, ExtendedUser userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .claim("idEnterprise",String.valueOf(userDetails.getIdEntreprise()))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = secretKey.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

    }
    // method to validate token

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return  extractExpirationDate(token).before(new Date());
    }

    // extract username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extract date expiration

    private  Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractIdEnterprise(String token) {
        final Claims claims = extractAllClaims(token);

        return claims.get("idEntreprise", String.class);
    }
    // extract claims
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //extract claim

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
