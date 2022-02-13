package kukukode.progem.apigateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtil {
    final private String key = "naijerLand69";


    /***
     *
     * @param token JWT token
     * @param userIDAttributeName name of the Attribute that holds the value to userID
     * @return returns the userID
     */
    public String extractUserName(String token, String userIDAttributeName) {
        var a = Jwts.parser().setSigningKey(key).parse(token);
        var b = (Map<String, String>) (a.getBody());
        return (b.get(userIDAttributeName));
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        return Jwts.builder()
                .setClaims(map)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, key)
                .setExpiration(new Date(System.currentTimeMillis() * 600))
                .compact();
    }

    /***
     *
     * @param token JWT token
     * @param userID The Expected UserID
     * @param userIDAttribute Attribute name that stores the UserID of the JWT token
     * @return true if valid, false if invalid
     */
    public boolean validateToken(String token, String userID, String userIDAttribute) {
        Jwt jwt = Jwts.parser().setSigningKey(key).parse(token);
        Map<String, Object> claims = (Map<String, Object>) jwt.getBody();
        String userIDFromJWT = (String) claims.get(userIDAttribute);
        return (userIDFromJWT.equals(userID) && !isTokenExpired(token));
    }

}
