package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.PackageRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PackageResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.entity.Package;
import edu.hackeru.evgenyzakalinsky.restour.error.PackageNotFoundException;
import edu.hackeru.evgenyzakalinsky.restour.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final ModelMapper modelMapper;
    private final PackageRepository packageRepository;

    @Override
    public PackageResponseDto createPackage(PackageRequestDto packageRequestDto) {

        var entity = modelMapper.map(packageRequestDto, Package.class);
        var saved = packageRepository.save(entity);
        return modelMapper.map(saved, PackageResponseDto.class);
    }

    @Override
    public List<PackageResponseDto> getAllPackages() {

        return packageRepository
                .findAll()
                .stream()
                .map(p -> modelMapper.map(p, PackageResponseDto.class))
                .toList();
    }

    @Override
    public List<PackageResponseDto> getPackageByDestination(String destination) {
        return packageRepository
                .findPackageByDestinationIgnoreCase(destination)
                .stream()
                .map(p -> modelMapper.map(p, PackageResponseDto.class))
                .toList();
    }

    private Package getPackageEntity(long id) {

        return packageRepository
                .findById(id)
                .orElseThrow(() -> new PackageNotFoundException(
                        "Package", id
                ));
    }

    @Override
    public PackageResponseDto getPackageById(long id) {

        Package pack = getPackageEntity(id);
        return modelMapper.map(pack, PackageResponseDto.class);
    }

    @Override
    public PackageResponseDto updatePackageById(PackageRequestDto dto, long id) {

        Package packageFromDb = getPackageEntity(id);

        packageFromDb.setTitle(dto.getTitle());
        packageFromDb.setDestination(dto.getDestination());
        packageFromDb.setDescription(dto.getDescription());
        packageFromDb.setTourRoute(dto.getTourRoute());
        packageFromDb.setGroupLimit(dto.getGroupLimit());
        packageFromDb.setGroupCurrent(dto.getGroupCurrent());
        packageFromDb.setAvailableDate(dto.getAvailableDate());
        packageFromDb.setPrice(dto.getPrice());

        var saved = packageRepository.save(packageFromDb);

        return modelMapper.map(saved, PackageResponseDto.class);
    }

    @Override
    public PackageResponseDto deletePackageById(long id) {

        Package pack = getPackageEntity(id);
        packageRepository.deleteById(id);
        return modelMapper.map(pack, PackageResponseDto.class);
    }
}
