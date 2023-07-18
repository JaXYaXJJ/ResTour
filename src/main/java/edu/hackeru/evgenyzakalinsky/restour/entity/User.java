package edu.hackeru.evgenyzakalinsky.restour.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date")
    private Date dob;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
