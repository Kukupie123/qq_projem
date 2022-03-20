package com.kukode.progem.auth_mc_v3.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    final private String key = "naijerLand69";
    //Attribute names
    final private String expiration = "exp";
    final private String initiatedAt = "iat";
    final private String subject = "sub";

    public String extractUserName(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        var exp = claims.get(expiration);
        var iat = claims.get(initiatedAt);
        long iatDate = ((Number) iat).longValue();

        return (String) claims.get(subject);
    }

    public String generateToken(String email) {
        int expiry = 60000 * 30; //expires after currentTime + 'expiry' millisecond
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, key)
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .compact();
    }

    private void validateExpiry(Map<String, Object> body) throws Exception {
        //Validate expiry date
        long dateIssuedMS = ((Number) body.get(initiatedAt)).longValue();
        long expiryDateMS = ((Number) body.get(expiration)).longValue();

        Calendar issuedDate = Calendar.getInstance();
        issuedDate.setTimeInMillis(dateIssuedMS);
        Calendar expiry = Calendar.getInstance();
        expiry.setTimeInMillis(expiryDateMS);
        System.out.println("Issued at " + issuedDate.getTime() + " expiry : " + expiry.getTime());
        //Validated successfully, validate expiration
        if (expiry.getTime().before(new Date(System.currentTimeMillis()))) {
            throw new Exception("Token expired");
        }
    }

}
