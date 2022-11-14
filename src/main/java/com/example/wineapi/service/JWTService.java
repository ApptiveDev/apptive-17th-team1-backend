package com.example.wineapi.service;

import com.example.wineapi.data.entity.Member;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String secretKey = "secretKey";
    private long expire = 1000L*60*60*24;
    public String createToken(Member member, String AutoLogin) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        if(AutoLogin.equals("true")) {
            return Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .claim("id", member.getId())
                    .setExpiration(new Date(System.currentTimeMillis() + 7*expire))
                    .signWith(signatureAlgorithm, secretKey.getBytes())
                    .compact();
        }else {
            return Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .claim("id", member.getId())
                    .setExpiration(new Date(System.currentTimeMillis() + expire))
                    .signWith(signatureAlgorithm, secretKey.getBytes())
                    .compact();
        }
    }

    public Claims getClaim(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public boolean checkTokenExp(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("[+] expire: " + claims.getExpiration());
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("[-] Token Expired");
            return false;
        } catch (JwtException e) {
            System.out.println("[-] Token Modified");
            return false;
        }
    }
}
