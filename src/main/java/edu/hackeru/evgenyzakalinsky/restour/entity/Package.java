package edu.hackeru.evgenyzakalinsky.restour.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "packages")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
})
public class Package {

    @Id
    @GeneratedValue
    private Long id;
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
    @Column(name = "available_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull
    private Date availableDate;
    private int price;

    @OneToMany(
            mappedBy = "pack",
            cascade = CascadeType.ALL
    )
    private Set<Comment> comments = new HashSet<>();
}
