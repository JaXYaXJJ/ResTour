package edu.hackeru.evgenyzakalinsky.restour.validation;

import edu.hackeru.evgenyzakalinsky.restour.entity.User;
import edu.hackeru.evgenyzakalinsky.restour.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);

        return user.isEmpty();
    }
}
