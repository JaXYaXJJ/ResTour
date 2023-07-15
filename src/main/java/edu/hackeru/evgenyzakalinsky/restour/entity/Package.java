package edu.hackeru.evgenyzakalinsky.restour.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

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
    private String content;
    @Column(name = "available_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date availableDate;
    @NotNull
    private int price;
}
