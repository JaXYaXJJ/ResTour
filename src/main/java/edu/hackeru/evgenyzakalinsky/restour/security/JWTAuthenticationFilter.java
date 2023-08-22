package edu.hackeru.evgenyzakalinsky.restour.security;

import edu.hackeru.evgenyzakalinsky.restour.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var token = getJWTFromRequest(request);

        if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
            var email = jwtProvider.getEmailFromToken(token);
            var user = userDetailsService.loadUserByUsername(email);
            var authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    null,
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) &&
                StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {

            return authHeader.substring("Bearer ".length());
        }
        return null;
    }
}
