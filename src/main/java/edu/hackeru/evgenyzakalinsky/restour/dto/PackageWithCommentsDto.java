package edu.hackeru.evgenyzakalinsky.restour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageWithCommentsDto {

    private Long id;
    private String comment;
}