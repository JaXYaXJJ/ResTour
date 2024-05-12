package edu.hackeru.evgenyzakalinsky.restour.repository;

import edu.hackeru.evgenyzakalinsky.restour.entity.PreJoining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreJoiningRepository extends JpaRepository<PreJoining, Long> {

    List<PreJoining> findPreJoiningByFirstNameIgnoreCase(String firstName);
    List<PreJoining> findPreJoiningByPhoneIgnoreCase(String phone);
    List<PreJoining> findPreJoiningByEmailIgnoreCase(String email);
}
