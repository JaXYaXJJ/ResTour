package edu.hackeru.evgenyzakalinsky.restour.validation;

import edu.hackeru.evgenyzakalinsky.restour.entity.Package;
import edu.hackeru.evgenyzakalinsky.restour.repository.PackageRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {

    private final PackageRepository packageRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        Optional<Package> pack = packageRepository.findByTitle(title);

        return pack.isEmpty();
    }
}
