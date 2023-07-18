package edu.hackeru.evgenyzakalinsky.restour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "reserve_package")
public class ReservePackage {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private LocalDate departure;
    @NotNull
    private LocalDate arrival;
}
