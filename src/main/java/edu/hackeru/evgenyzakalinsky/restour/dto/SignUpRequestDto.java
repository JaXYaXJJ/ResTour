package edu.hackeru.evgenyzakalinsky.restour.dto;

import edu.hackeru.evgenyzakalinsky.restour.validation.UniqueEmail;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20, message = "First Name must contain at least 2 letters")
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20, message = "Last Name must contain at least 2 letters")
    private String lastName;
    private Date dob;
    private String phone;
    @NotNull
    @Email
    @NotEmpty
    @UniqueEmail
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{6,20}$",
            message = "Password must contain at least 6 characters, " +
                    "one or more lower case letters, uppercase letter, symbol, digits"
    )
    private String password;
}
