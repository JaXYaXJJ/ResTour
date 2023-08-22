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
public class SignInRequestDto {

    @NotNull
    @Email
    @NotEmpty
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
