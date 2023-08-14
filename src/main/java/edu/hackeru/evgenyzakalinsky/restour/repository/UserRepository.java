package edu.hackeru.evgenyzakalinsky.restour.repository;

import edu.hackeru.evgenyzakalinsky.restour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLastNameIgnoreCaseOrEmailIgnoreCase(String lastName, String email);
    Optional<User> findByLastNameIgnoreCase(String lastName);
    Optional<User> findByEmailIgnoreCase(String email);
    Boolean existsUserByLastNameIgnoreCase(String lastName);
    Boolean existsUserByEmailIgnoreCase(String email);
}
