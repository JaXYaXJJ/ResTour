package edu.hackeru.evgenyzakalinsky.restour.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class PackageSecurityConfig {

    private final JWTAuthenticationFilter filter;

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http
                .addFilterBefore(filter, BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/auth/**").permitAll();
                    auth.requestMatchers("/api/v1/**").authenticated();
                    auth.anyRequest().permitAll();
                })
                .httpBasic(basic -> basic.authenticationEntryPoint(
                        new PackageAuthenticationEntryPoint()
                ))
                .build();
    }
}
