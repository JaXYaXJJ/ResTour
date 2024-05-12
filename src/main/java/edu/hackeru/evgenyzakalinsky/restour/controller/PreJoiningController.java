package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.PreJoiningRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PreJoiningResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.service.PreJoiningService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pre-joining")
public class PreJoiningController {

    private final PreJoiningService preJoiningService;

    @PostMapping
    @PermitAll
    public ResponseEntity<PreJoiningResponseDto> createPreJoining(
            @RequestBody @Valid PreJoiningRequestDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        var saved = preJoiningService.createPreJoining(
                dto
        );
        var uri = uriBuilder
                .path("/api/v1/pre-joining/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(saved);
    }
}
