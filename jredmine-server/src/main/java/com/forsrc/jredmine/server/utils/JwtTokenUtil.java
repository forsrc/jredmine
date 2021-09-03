package com.forsrc.jredmine.server.utils;

import java.security.SignatureException;
import java.util.Date;

import com.forsrc.jredmine.server.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtTokenUtil {

    // echo -n "forsrc" | openssl sha512 -hmac "forsrc"
    private static final String jwtSecret = "72555721aa3aa8e8ce94eb82d949018755a556e164d7f774ea077b3e77ea6de31783a6f3c7261fbcd5ab37e3c2c93fc89d4e14663e17d4c94e9f4df0322ca1db";
    private static final String jwtIssuer = "forsrc.com";

    public static String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public static String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public static void validate(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {

        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

    }
}