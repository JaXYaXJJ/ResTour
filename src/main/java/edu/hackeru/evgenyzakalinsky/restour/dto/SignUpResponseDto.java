package edu.hackeru.evgenyzakalinsky.restour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String phone;
    private String email;
}
