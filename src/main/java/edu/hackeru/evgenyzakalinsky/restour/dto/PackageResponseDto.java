package edu.hackeru.evgenyzakalinsky.restour.dto;

import jakarta.validation.constraints.NotNull;
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
    private String content;
    private Date availableDate;
    private int price;
}
