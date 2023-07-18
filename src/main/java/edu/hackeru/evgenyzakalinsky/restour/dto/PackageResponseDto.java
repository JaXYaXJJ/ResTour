package edu.hackeru.evgenyzakalinsky.restour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageResponseDto {

    private Long id;
    private String title;
    private String destination;
    private String description;
    private String tourRoute;
    private int groupLimit;
    private int groupCurrent;
    private Date availableDate;
    private int price;
}
