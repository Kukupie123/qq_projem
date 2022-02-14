package kukukode.progem.jwt_mc.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class JWTUtil {
    final private String key = "naijerLand69";

    public String extractUserName(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        var exp = claims.get("exp");
        var iat = claims.get("iat");
        long iatDate = ((Number) iat).longValue();

        return (String) claims.get("sub");
    }

    public String generateToken(String email) {
        int expiry = 60000 * 5; //expires after currentTime + 'expiry' millisecond
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, key)
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .compact();
    }

    private void validateExpiry(Map<String, Object> body) throws Exception {
        //Validate expiry date
        long dateIssuedMS = ((Number) body.get("iat")).longValue();
        long expiryDateMS = ((Number) body.get("exp")).longValue();

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
