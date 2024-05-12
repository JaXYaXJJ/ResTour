package edu.hackeru.evgenyzakalinsky.restour.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreJoiningRequestDto {

    @NotNull
    private String firstName;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    private String title;
}
