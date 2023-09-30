package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.SignInRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.SignInResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.SignUpRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.SignUpResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.security.JWTProvider;
import edu.hackeru.evgenyzakalinsky.restour.service.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsServiceImpl authService;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto dto
    ) {
        val response = authService.signUp(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto dto
    ) {
        var user = authService.loadUserByUsername(dto.getEmail());

        var savedPass = user.getPassword();
        var givenPass = dto.getPassword();

        if (passwordEncoder.matches(givenPass, savedPass)) {

            var token = jwtProvider.generateToken(user.getUsername());
            return ResponseEntity.ok(new SignInResponseDto(token));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SignUpResponseDto> deleteUserById(
            @Valid @PathVariable long id
    ) {
        return ResponseEntity.ok(authService.deleteUser(id));
    }
}
