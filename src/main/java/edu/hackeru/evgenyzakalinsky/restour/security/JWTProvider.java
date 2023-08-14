package edu.hackeru.evgenyzakalinsky.restour.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTProvider {
    @Value("${edu.hackeru.evgenyzakalinsky.restour.secret}")
    private String secret;

    @Value("${edu.hackeru.evgenyzakalinsky.restour.expires}")
    private Long expires;
}
