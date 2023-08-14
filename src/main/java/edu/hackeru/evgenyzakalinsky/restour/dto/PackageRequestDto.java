package edu.hackeru.evgenyzakalinsky.restour.dto;

import edu.hackeru.evgenyzakalinsky.restour.validation.UniqueTitle;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 255)
    @UniqueTitle
    private String title;
    @NotNull
    private String destination;
    @NotNull
    @Size(min = 2, max = 1000)
    private String description;
    @NotNull
    @Size(min = 2)
    private String tourRoute;
    private int groupLimit;
    private int groupCurrent;
    private Date availableDate;
    private int price;
}
