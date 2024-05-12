package edu.hackeru.evgenyzakalinsky.restour.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Package pack;
}
