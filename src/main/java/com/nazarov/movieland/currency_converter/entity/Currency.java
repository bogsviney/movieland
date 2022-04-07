package com.nazarov.movieland.currency_converter.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "currency"
)
public class Currency {
    @Id
    private Long r030;
    private String txt;
    private double rate;
    private String name;
    private LocalDate exchangedate;
}
