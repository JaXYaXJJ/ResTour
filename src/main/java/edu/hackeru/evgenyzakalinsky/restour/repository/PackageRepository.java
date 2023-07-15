package edu.hackeru.evgenyzakalinsky.restour.repository;

import edu.hackeru.evgenyzakalinsky.restour.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Long> {

    List<Package> findPackageByDestinationIgnoreCase(String destination);
}
