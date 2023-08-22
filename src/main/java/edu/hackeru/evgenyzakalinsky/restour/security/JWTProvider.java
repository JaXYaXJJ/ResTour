package edu.hackeru.evgenyzakalinsky.restour.security;

import edu.hackeru.evgenyzakalinsky.restour.error.BadRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTProvider {
    @Value("${edu.hackeru.evgenyzakalinsky.restour.secret}")
    private String secret;

    @Value("${edu.hackeru.evgenyzakalinsky.restour.expires}")
    private Long expires;

    private static Key mySecretKey;

    @PostConstruct
    private void init() {
        mySecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(String email) {

        var currentDate = new Date();
        var expiresDate = new Date(currentDate.getTime() + expires);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expiresDate)
                .signWith(mySecretKey)
                .compact();
    }

    public boolean validateToken(String jwt) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(mySecretKey)
                    .build()
                    .parseClaimsJws(jwt);

        } catch (ExpiredJwtException e) {
            throw new BadRequestException("Token", "Expired");
        } catch (MalformedJwtException e) {
            throw new BadRequestException("Token", "Invalid");
        } catch (JwtException e) {
            throw new BadRequestException("Token", "Exception: " + e.getMessage());
        }
        return true;
    }

    public String getEmailFromToken(String jwt) {

        return Jwts.parserBuilder()
                .setSigningKey(mySecretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
