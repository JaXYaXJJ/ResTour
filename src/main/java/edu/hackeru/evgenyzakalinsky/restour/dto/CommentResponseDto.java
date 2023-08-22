package edu.hackeru.evgenyzakalinsky.restour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private long id;
    private SignUpResponseDto signUpUser;
    private String comment;
}
