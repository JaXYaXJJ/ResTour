package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.PackageRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PackageResponseDto;

import java.util.List;

public interface PackageService {
    PackageResponseDto createPackage(PackageRequestDto packageRequestDto);
    List<PackageResponseDto> getAllPackages();
    List<PackageResponseDto> getPackageByDestination(String destination);
    PackageResponseDto getPackageById(long id);
    PackageResponseDto updatePackageById(PackageRequestDto dto, long id);
    PackageResponseDto deletePackageById(long id);
}
