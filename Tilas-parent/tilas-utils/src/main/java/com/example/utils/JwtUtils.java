package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public final class JwtUtils {

    private static final String SIGN_KEY = "dGhpcyBhIGp3dFRlc3Q=";  //秘钥
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;  //12hours

    private JwtUtils() {
    }

    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
