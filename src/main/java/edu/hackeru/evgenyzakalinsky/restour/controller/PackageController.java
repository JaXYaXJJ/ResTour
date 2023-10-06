package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.PackageRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PackageResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class PackageController {

    private final PackageService packageService;

    @PostMapping("/create_tour")
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
    @Operation(summary = "Get all packages")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                schema = @Schema(implementation = PackageResponseDto.class)
            ))}),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
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

    @Operation(summary = "Get a package by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Found a package",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PackageResponseDto.class)
                            ))}),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid ID supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404", description = "Package not found",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDto> getPackageById(
            @Valid @PathVariable @Parameter(description = "ID of package to be searched") long id
    ) {
        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PackageResponseDto> updatePackageById(
            @Valid @PathVariable long id,
            @Valid @RequestBody PackageRequestDto dto
    ) {
        return ResponseEntity.ok(packageService.updatePackageById(dto, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PackageResponseDto> deletePackageById(
            @Valid @PathVariable long id
    ) {
        return ResponseEntity.ok(packageService.deletePackageById(id));
    }
}
