package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.SignInRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.SignUpRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.SignUpResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.security.JWTProvider;
import edu.hackeru.evgenyzakalinsky.restour.service.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/signup/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SignUpResponseDto> signUpAdmin(
            @RequestBody @Valid SignUpRequestDto dto
    ) {
        val response = authService.signUpAdmin(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(
            @RequestBody @Valid SignInRequestDto dto
    ) {
        var user = authService.loadUserByUsername(dto.getEmail());
        var savedPass = user.getPassword();
        var givenPass = dto.getPassword();
        val role = user.getAuthorities()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getAuthority()))
                .toList();

        if (passwordEncoder.matches(givenPass, savedPass)) {

            var token = jwtProvider.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of("jwt", token, "userRole", role));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<SignUpResponseDto>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
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
