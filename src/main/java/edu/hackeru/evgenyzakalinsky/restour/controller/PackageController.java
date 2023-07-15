package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.PackageRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PackageResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.service.PackageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @GetMapping("/destination")
    public ResponseEntity<List<PackageResponseDto>> byDestination(
            @RequestParam (value = "destination") String destination
    ) {
        return ResponseEntity.ok(
                packageService.getPackageByDestination(destination));
    }

    @PostMapping
    public ResponseEntity<PackageResponseDto> createPackage(
            @RequestBody @Valid PackageRequestDto dto, UriComponentsBuilder uriBuilder
    ) {
        var saved = packageService.createPackage(dto);
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

    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDto> getPackageById(
            @Valid @NotNull @PathVariable long id) {

        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDto> updatePackageById(
            @Valid @NotNull @PathVariable long id,
            @Valid @RequestBody PackageRequestDto dto) {

        return ResponseEntity.ok(packageService.updatePackageById(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PackageResponseDto> deletePackageById(
            @Valid @NotNull @PathVariable long id) {

        return ResponseEntity.ok(packageService.deletePackageById(id));
    }

}
