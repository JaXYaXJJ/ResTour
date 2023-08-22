package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.PackageRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PackageResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.service.PackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/packages")
public class PackageController {

    private final PackageService packageService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PackageResponseDto> createPackage(
            @RequestBody @Valid PackageRequestDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        var saved = packageService.createPackage(
                dto
        );
        var uri = uriBuilder
                .path("/api/v1/packages/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<PackageResponseDto>> getAllPackages() {

        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @GetMapping("/destination")
    public ResponseEntity<List<PackageResponseDto>> getPackageByDestination(
            @RequestParam (value = "destination") String destination
    ) {
        return ResponseEntity.ok(
                packageService.getPackageByDestination(destination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDto> getPackageById(
            @Valid @PathVariable long id
    ) {
        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDto> updatePackageById(
            @Valid @PathVariable long id,
            @Valid @RequestBody PackageRequestDto dto
    ) {
        return ResponseEntity.ok(packageService.updatePackageById(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PackageResponseDto> deletePackageById(
            @Valid @PathVariable long id
    ) {
        return ResponseEntity.ok(packageService.deletePackageById(id));
    }

}
