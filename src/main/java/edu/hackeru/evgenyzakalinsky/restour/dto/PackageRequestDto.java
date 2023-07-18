package edu.hackeru.evgenyzakalinsky.restour.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageRequestDto {

    @NotNull
    private String title;
    @NotNull
    private String destination;
    @NotNull
    private String description;
    @NotNull
    private String tourRoute;
    private int groupLimit;
    private int groupCurrent;
    private Date availableDate;
    private int price;
}
